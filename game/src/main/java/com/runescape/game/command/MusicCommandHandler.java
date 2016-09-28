package com.runescape.game.command;

import com.runescape.game.model.player.Player;
import com.runescape.game.msg.MusicMessage;

public class MusicCommandHandler extends CommandHandler {

	public MusicCommandHandler() {
		super("music");
	}

	@Override
	public void handle(Player player, String[] arguments) {
		if (arguments.length != 2) {
			player.sendMessage("Syntax: ::music id volume");
			return;
		}
		int id = Integer.parseInt(arguments[0]);
		int volume = Integer.parseInt(arguments[1]);
		player.send(new MusicMessage(id, volume));
	}

}
