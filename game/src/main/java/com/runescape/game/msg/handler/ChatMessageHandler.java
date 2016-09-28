package com.runescape.game.msg.handler;

import com.runescape.game.model.player.Player;
import com.runescape.game.msg.ChatMessage;

public final class ChatMessageHandler extends MessageHandler<ChatMessage> {

	@Override
	public void handle(Player player, ChatMessage message) {
		player.setChatMessage(message);
	}

}
