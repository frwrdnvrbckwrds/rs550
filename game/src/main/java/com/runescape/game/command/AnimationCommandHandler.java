package com.runescape.game.command;

import com.runescape.game.model.mob.Animation;
import com.runescape.game.model.player.Player;

public class AnimationCommandHandler extends CommandHandler {

	public AnimationCommandHandler() {
		super("anim");
	}

	@Override
	public void handle(Player player, String[] arguments) {
		if (player.getRights() < 2) {
			return;
		}
		
		if (arguments.length != 1) {
			player.sendMessage("Syntax: ::anim id");
			return;
		}
		
		int id = Integer.parseInt(arguments[0]);
		player.playAnimation(new Animation(id));
	}

}
