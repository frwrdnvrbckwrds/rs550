package com.runescape.game.msg.handler;

import com.runescape.game.model.item.Item;
import com.runescape.game.model.item.itemonplayer.ChristmasCracker;
import com.runescape.game.model.player.Player;
import com.runescape.game.msg.ItemOnPlayerMessage;

public class ItemOnPlayerMessageHandler  extends MessageHandler<ItemOnPlayerMessage> {
	@Override
	public void handle(Player player, ItemOnPlayerMessage message) {
		int localPlayerId = (message.getLocalPlayerId() - 2);
		int slotId = message.getSlotId();
		int itemId = message.getItemId();
		Player other = player.getLocalPlayers().get(localPlayerId);
		if (other != null) {
			Item item = player.getInventory().get(slotId);
			if (item != null) {
				if (item.getId() == itemId) {
					if (itemId == 962) {
						new ChristmasCracker().handle(player, message);
					}
				}
			}
			return;
		}
	}

}
