package com.runescape.game.command;

import com.runescape.game.model.player.Player;
import com.runescape.game.msg.ConfigMessage;

public final class ConfigCommandHandler extends CommandHandler {

	public ConfigCommandHandler() {
		super("config");
	}

	@Override
	public void handle(Player player, String[] arguments) {
		if (player.getRights() < 2)
			return;

		if (arguments.length != 2) {
			player.sendMessage("Syntax: ::config [id] [value]");
			return;
		}

		int id = Integer.parseInt(arguments[0]);
		int value = Integer.parseInt(arguments[1]);

		player.send(new ConfigMessage(id, value));
	}

}
