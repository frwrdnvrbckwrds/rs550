package com.runescape.game.msg.handler;

import com.runescape.game.model.Interface;
import com.runescape.game.model.player.Player;
import com.runescape.game.msg.SwapItemsMessage;

public final class SwapItemsMessageHandler extends MessageHandler<SwapItemsMessage> {

	@Override
	public void handle(Player player, SwapItemsMessage message) {
		if (message.getId() == Interface.INVENTORY && message.getSlot() == 0) {
			player.getInventory().swap(message.getSource(), message.getDestination());
		}
	}

}
