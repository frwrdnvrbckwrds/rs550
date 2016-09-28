package com.runescape.game.button;

import com.runescape.game.model.Interface;
import com.runescape.game.model.player.Player;

public final class LogoutButtonHandler extends ButtonHandler {

	public LogoutButtonHandler() {
		super(Interface.LOGOUT);
	}

	@Override
	public void handle(Player player, int slot, int parameter) {
		if (slot == 6) {
			player.logout();
		}
	}

}
