package com.runescape.game.msg.handler;

import com.runescape.game.model.Interface;
import com.runescape.game.model.item.Item;
import com.runescape.game.model.player.Player;
import com.runescape.game.msg.DropItemMessage;
import com.runescape.game.msg.GroundItemMessage;

public class DropItemMessageHandler extends MessageHandler<DropItemMessage> {

	@Override
	public void handle(Player player, DropItemMessage message) {
		if (message.getInterfaceId() == Interface.INVENTORY) {
			Item item = player.getInventory().get(message.getSlot());
			if (item != null) {
				player.send(new GroundItemMessage(item, player.getPosition()));
				player.getInventory().remove(item, message.getSlot());
			}
		}
	}

}
