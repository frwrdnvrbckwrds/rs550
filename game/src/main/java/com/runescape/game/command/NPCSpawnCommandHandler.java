package com.runescape.game.command;

import com.runescape.game.model.Npc;
import com.runescape.game.model.World;
import com.runescape.game.model.mob.Direction;
import com.runescape.game.model.player.Player;
import com.runescape.game.update.NpcDescriptor;

public class NPCSpawnCommandHandler extends CommandHandler {

	public NPCSpawnCommandHandler() {
		super("npc");
	}
	
	@Override
	public void handle(Player player, String[] arguments) {
		if (player.getRights() < 2) {
			return;
		}
		
		if (arguments.length != 1) {
			player.sendMessage("Syntax: ::npc [id]");
			return;
		}
		
		int npcId = Integer.parseInt(arguments[0]);
		Npc npc = new Npc(npcId);
		npc.setDirections(Direction.NONE, Direction.NONE);
		npc.setPosition(player.getPosition());
		NpcDescriptor.create(npc);
		World.getWorld().getNpcs().add(npc);
	}

}
