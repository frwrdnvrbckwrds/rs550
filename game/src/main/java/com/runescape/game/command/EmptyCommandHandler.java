package com.runescape.game.command;

import com.runescape.game.model.player.Player;

public final class EmptyCommandHandler extends CommandHandler {

	public EmptyCommandHandler() {
		super("empty");
	}

	@Override
	public void handle(Player player, String[] arguments) {
		if (player.getRights() < 2)
			return;

		if (arguments.length != 0) {
			player.sendMessage("Syntax: ::empty");
			return;
		}

		player.getInventory().empty();
	}

}
