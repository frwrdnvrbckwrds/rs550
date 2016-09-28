package com.runescape.game.io.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.runescape.game.model.SkillSet;
import com.runescape.game.model.player.Player;

public class SkillsTable extends Table {
	private final PreparedStatement loadStatement;
	private final PreparedStatement saveStatement;
	
	public SkillsTable(Connection connection) throws SQLException {
		this.loadStatement = connection.prepareStatement("SELECT * FROM skills WHERE player_id = ?");
		this.saveStatement = connection.prepareStatement("REPLACE INTO skills (player_id, skill_id, skill_xp) VALUES (?, ?, ?);");
	}
	
	@Override
	public void load(Player player) throws SQLException, IOException {
		loadStatement.setInt(1, player.getDatabaseId());

		try (ResultSet set = loadStatement.executeQuery()) {
			if (!set.first())
				return;

			int xp = set.getInt("skill_xp");
			
			SkillSet skills = player.getSkillSet();
			for (int id = set.getInt("skill_id"); id < skills.size(); id++) {
				player.getSkillSet().addExperience(id, xp);
				player.getSkillSet().setCurrentLevel(id, player.getSkillSet().getLevel(id));
			}
		}
	}

	@Override
	public void save(Player player) throws SQLException {
		saveStatement.setInt(1, player.getDatabaseId());
		for (int i = 0; i < 24; i++) {
			saveStatement.setInt(2, i);
			saveStatement.setDouble(3, player.getSkillSet().getExperience(i));
		}
		saveStatement.execute();
	}

}