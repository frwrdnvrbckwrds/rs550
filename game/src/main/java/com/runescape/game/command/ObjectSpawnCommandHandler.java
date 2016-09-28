package com.runescape.game.command;

import com.runescape.game.model.World;
import com.runescape.game.model.object.GameObject;
import com.runescape.game.model.object.ObjectOrientation;
import com.runescape.game.model.object.ObjectType;
import com.runescape.game.model.player.Player;

public class ObjectSpawnCommandHandler extends CommandHandler {

	public ObjectSpawnCommandHandler() {
		super("obj");
	}

	@Override
	public void handle(Player player, String[] arguments) {
		if (player.getRights() < 2) {
			return;
		}
		if (arguments.length != 1) {
			player.sendMessage("Syntax: ::obj [id]");
			return;
		}
		
		int objectId = Integer.parseInt(arguments[0]);
		World.getWorld().getRegionManager().getRegion(player.getPosition()).addObject(new GameObject(objectId, ObjectType.TYPE_0.getId(), player.getPosition(), ObjectOrientation.NORTH));
	}

}
