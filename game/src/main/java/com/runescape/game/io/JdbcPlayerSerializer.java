package com.runescape.game.io;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runescape.game.io.jdbc.ItemsTable;
import com.runescape.game.io.jdbc.PlayersTable;
import com.runescape.game.io.jdbc.SettingsTable;
import com.runescape.game.io.jdbc.SkillsTable;
import com.runescape.game.io.jdbc.Table;
import com.runescape.game.model.item.Inventory;
import com.runescape.game.model.player.Player;
import com.runescape.game.net.login.LoginResponse;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;

public final class JdbcPlayerSerializer extends PlayerSerializer implements Closeable {

	private static final Logger logger = LoggerFactory.getLogger(JdbcPlayerSerializer.class);

	private final Connection connection;
	private final PreparedStatement loginStatement;
	private final Table[] tables;

	public JdbcPlayerSerializer(String url, String username, String password) throws SQLException {
		connection = DriverManager.getConnection(url, username, password);
		connection.setAutoCommit(false);
		loginStatement = connection.prepareStatement("SELECT id, password FROM players WHERE username = ?;");
		tables = new Table[] {
			new PlayersTable(connection),
			new SkillsTable(connection),
			new SettingsTable(connection),
			new ItemsTable(connection, "inventory") {
				@Override
				public Inventory getInventory(Player player) {
					return player.getInventory();
				}
			},
			new ItemsTable(connection, "equipment") {
				@Override
				public Inventory getInventory(Player player) {
					return player.getEquipment();
				}
			},
			new ItemsTable(connection, "bank") {
				@Override
				public Inventory getInventory(Player player) {
					return player.getBank();
				}
			},
		};
	}

	@Override
	public SerializeResult load(String username, String password) {
		try {
			loginStatement.setString(1, username);
			try (ResultSet set = loginStatement.executeQuery()) {
				if (set.first()) {
					int id = set.getInt("id");
					String hashedPassword = set.getString("password");

					String rehashed = hashedPassword.replace("$2y$", "$2a$");
					if (BCrypt.checkpw(password, rehashed)) {
						Player player = new Player();
						player.setDatabaseId(id);
						player.setPassword(password);

						for (Table table : tables)
							table.load(player);

						return new SerializeResult(LoginResponse.STATUS_OK, player);
					}
				}

				return new SerializeResult(LoginResponse.STATUS_INVALID_PASSWORD);
			}
		} catch (SQLException | IOException ex) {
			logger.warn("Loading player " + username + " failed.", ex);
			return new SerializeResult(LoginResponse.STATUS_COULD_NOT_COMPLETE);
		}
	}

	@Override
	public void save(Player player) {
		try {
			for (Table table : tables)
				table.save(player);

			connection.commit();
		} catch (SQLException | IOException ex) {
			try {
				connection.rollback();
			} catch (SQLException innerEx) {
				/* ignore rollback failure, not much we can do */
			}

			logger.warn("Saving player " + player.getUsername() + " failed.", ex);
		}
	}

	@Override
	public void close() throws IOException {
		try {
			connection.close();
		} catch (SQLException ex) {
			throw new IOException(ex);
		}
	}

}