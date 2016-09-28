package com.runescape.game.msg.handler;

import com.runescape.game.model.Interface;
import com.runescape.game.model.consumables.Food;
import com.runescape.game.model.player.Player;
import com.runescape.game.model.skills.herblore.Herblore;
import com.runescape.game.msg.ClickItemMessage;

public class ClickItemMessageHandler extends MessageHandler<ClickItemMessage> {

	@Override
	public void handle(Player player, ClickItemMessage message) {
		if (message.getInterfaceId() == Interface.INVENTORY) {
			if (Herblore.cleanHerb(player, message.getSlotId())) {
				return;
			}
			
			for (Food food : Food.values()) {
				if (food.getId() == message.getItemId()) {
					if (Food.consume(player, message.getSlotId())) {
						return;
					}
				}
			}
		}
	}

}
