package com.runescape.game.msg.handler;

import com.runescape.game.model.Interface;
import com.runescape.game.model.Position;
import com.runescape.game.model.World;
import com.runescape.game.model.object.GameObject;
import com.runescape.game.model.player.Player;
import com.runescape.game.msg.ItemOnObjectMessage;

public class ItemOnObjectMessageHandler extends MessageHandler<ItemOnObjectMessage> {

	@Override
	public void handle(Player player, ItemOnObjectMessage message) {
		if (message.getInterfaceId() == Interface.INVENTORY) {
			if (player.getInventory().get(message.getSlotId()).getId() == message.getItemId()) {
				GameObject object = World.getWorld().getRegionManager().getRegion(player.getPosition()).getObject(new Position(message.getObjectX(), message.getObjectY()));
				if (object.getId() == message.getObjectId()) {
					//TODO: logic
					return;
				}
			}
		}
	}

}
