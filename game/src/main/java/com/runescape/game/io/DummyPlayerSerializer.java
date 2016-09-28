package com.runescape.game.io;

import com.runescape.game.model.Position;
import com.runescape.game.model.player.Player;
import com.runescape.game.net.login.LoginResponse;

public final class DummyPlayerSerializer extends PlayerSerializer {

	@Override
	public SerializeResult load(String username, String password) {
		Player player = new Player();
		player.setUsername(username);
		player.setPassword(password);
		player.setRights(2);
		player.setPosition(new Position(3232, 3232));
		return new SerializeResult(LoginResponse.STATUS_OK, player);
	}

	@Override
	public void save(Player player) {
		/* discard player */
	}

}