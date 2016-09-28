package com.runescape.game.command;

import com.runescape.game.model.player.Player;

public abstract class CommandHandler {

	private final String name;

	public CommandHandler(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public abstract void handle(Player player, String[] arguments);

}
