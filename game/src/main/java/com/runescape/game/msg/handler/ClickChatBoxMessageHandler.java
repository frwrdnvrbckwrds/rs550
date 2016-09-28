package com.runescape.game.msg.handler;

import com.runescape.game.model.player.Player;
import com.runescape.game.msg.ClickChatBoxMessage;

public class ClickChatBoxMessageHandler extends MessageHandler<ClickChatBoxMessage> {
	private static final int TRADE = 0;
	
	@Override
	public void handle(Player player, ClickChatBoxMessage message) {
		if (message.getType() == TRADE) {
			int id = message.getLocalPlayerId() - 2;
			if (id < 0) {
				id = 0;
			}
			final Player other = player.getLocalPlayers().get(id);
			if (other != null) {
				player.getTrade().acceptRequest(other);
			} else {
				player.sendMessage("Cannot find player.");
			}
			return;
		}
	}

}
