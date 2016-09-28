package com.runescape.game.msg.handler;

import com.runescape.game.model.player.Player;
import com.runescape.game.msg.FriendMessage;

public class AddFriendMessageHandler extends MessageHandler<FriendMessage> {

	@Override
	public void handle(Player player, FriendMessage message) {
		player.getFriends().addFriend(message.getName());
	}

}
