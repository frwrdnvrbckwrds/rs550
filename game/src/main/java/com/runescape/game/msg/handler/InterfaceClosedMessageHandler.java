package com.runescape.game.msg.handler;

import com.runescape.game.model.player.Player;
import com.runescape.game.msg.InterfaceClosedMessage;

public final class InterfaceClosedMessageHandler extends MessageHandler<InterfaceClosedMessage> {

	@Override
	public void handle(Player player, InterfaceClosedMessage message) {
		
		if (player.getInterfaceSet().allTabsClosed()) {
			player.getInterfaceSet().openAllTabs();
		}
		
		if (player.getTrade().isTrading()) {
			player.getTrade().getSession().declineTrade();
			return;
		}
		
	}

}
