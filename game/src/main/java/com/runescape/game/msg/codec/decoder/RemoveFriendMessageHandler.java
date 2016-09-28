package com.runescape.game.msg.codec.decoder;

import com.runescape.game.model.player.Player;
import com.runescape.game.msg.FriendMessage;
import com.runescape.game.msg.handler.MessageHandler;

public class RemoveFriendMessageHandler extends MessageHandler<FriendMessage>{

	@Override
	public void handle(Player player, FriendMessage message) {
		player.getFriends().deleteFriend(message.getName());
	}

}
