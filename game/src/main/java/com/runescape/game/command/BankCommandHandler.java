package com.runescape.game.command;

import com.runescape.game.model.player.Player;

public class BankCommandHandler extends CommandHandler {

	public BankCommandHandler() {
		super("bank");
	}

	@Override
	public void handle(Player player, String[] arguments) {
		if (player.getRights() < 0) {
			return;
		}
		player.getInterfaceSet().openBank();
	}

}
