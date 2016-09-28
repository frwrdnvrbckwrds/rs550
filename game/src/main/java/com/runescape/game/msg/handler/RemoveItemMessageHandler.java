package com.runescape.game.msg.handler;

import com.runescape.game.model.Interface;
import com.runescape.game.model.item.Equipment;
import com.runescape.game.model.item.Item;
import com.runescape.game.model.player.Player;
import com.runescape.game.msg.RemoveItemMessage;

public final class RemoveItemMessageHandler extends MessageHandler<RemoveItemMessage> {

	@Override
	public void handle(Player player, RemoveItemMessage message) {
		if (message.getId() == Interface.EQUIPMENT && message.getSlot() == 29) { //slot == 28
			Item item = player.getEquipment().get(message.getItemSlot());
			if (item == null || item.getId() != message.getItemId())
				return;
			Equipment.remove(player, message.getItemSlot());
		}
	}

}
