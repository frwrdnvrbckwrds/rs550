package com.runescape.game.msg.handler;

import com.runescape.game.model.player.Player;
import com.runescape.game.msg.Message;

public abstract class MessageHandler<T extends Message> {

	public abstract void handle(Player player, T message);

}
