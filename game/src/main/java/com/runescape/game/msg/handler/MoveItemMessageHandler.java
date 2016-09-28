package com.runescape.game.msg.handler;

import com.runescape.game.model.item.Item;
import com.runescape.game.model.player.Player;
import com.runescape.game.msg.MoveItemMessage;

public class MoveItemMessageHandler extends MessageHandler<MoveItemMessage> {

	@Override
	public void handle(Player player, MoveItemMessage message) {
		int oldSlot = message.getOldSlotId();
		int newSlot = message.getNewSlotId();
		Item item = player.getInventory().get(oldSlot);
		player.getInventory().set(newSlot, item);
		player.getInventory().set(oldSlot, null);
	}

}
