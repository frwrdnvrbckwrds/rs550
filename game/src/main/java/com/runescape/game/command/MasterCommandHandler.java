package com.runescape.game.command;

import com.runescape.game.model.SkillSet;
import com.runescape.game.model.player.Player;

public final class MasterCommandHandler extends CommandHandler {

	public MasterCommandHandler() {
		super("master");
	}

	@Override
	public void handle(Player player, String[] arguments) {
		if (player.getRights() < 2)
			return;

		if (arguments.length != 0) {
			player.sendMessage("Syntax: ::master");
			return;
		}

		SkillSet skills = player.getSkillSet();
		for (int id = 0; id < skills.size(); id++) {
			skills.addExperience(id, SkillSet.MAXIMUM_EXPERIENCE);
			skills.setCurrentLevel(id, skills.getLevel(id));
		}
	}

}