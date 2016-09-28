package com.runescape.game.msg.handler;

import com.runescape.game.model.Interface;
import com.runescape.game.model.item.Equipment;
import com.runescape.game.model.item.Item;
import com.runescape.game.model.player.Player;
import com.runescape.game.msg.EquipItemMessage;

public final class EquipItemMessageHandler extends MessageHandler<EquipItemMessage> {

	public void handle(Player player, EquipItemMessage message) {
		
		if (message.getInterfaceId() == Interface.INVENTORY && message.getSlot() == 0) {
			Item item = player.getInventory().get(message.getInventorySlot());
			if (item == null || item.getId() != message.getItemId()) {
				return;
			}
			Equipment.equip(player, message.getInventorySlot());
		}
	}

}

