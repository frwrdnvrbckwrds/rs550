package com.runescape.game.command;

import com.runescape.game.model.player.Player;

public class TestCommandHandler extends CommandHandler {
	
	public TestCommandHandler() {
		super("test");
	}

	@Override
	public void handle(Player player, String[] arguments) {
		player.getAppearance().showAppearanceInterface();
	}

}