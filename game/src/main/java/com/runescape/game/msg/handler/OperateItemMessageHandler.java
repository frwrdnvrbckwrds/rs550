package com.runescape.game.msg.handler;

import com.runescape.game.action.impl.EmoteAction;
import com.runescape.game.model.item.Equipment;
import com.runescape.game.model.player.Player;
import com.runescape.game.msg.OperateItemMessage;

public class OperateItemMessageHandler extends MessageHandler<OperateItemMessage> {

	@Override
	public void handle(Player player, OperateItemMessage message) {
		if (message.getSlotId() == Equipment.CAPE) {
			if (message.getItemId() == 9750 || message.getItemId() == 9751) {
				player.startAction(new EmoteAction(player, 4981, 828, 11));
				return;
			} 
		} else if (message.getSlotId() == Equipment.SHIELD) {
			return;
		}
		player.sendMessage("Nothing interesting happens.");
	}

}
