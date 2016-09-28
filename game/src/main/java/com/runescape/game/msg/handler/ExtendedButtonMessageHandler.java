package com.runescape.game.msg.handler;

import com.runescape.game.button.ButtonDispatcher;
import com.runescape.game.model.player.Player;
import com.runescape.game.msg.ExtendedButtonMessage;

public final class ExtendedButtonMessageHandler extends MessageHandler<ExtendedButtonMessage> {

	private final ButtonDispatcher dispatcher;

	public ExtendedButtonMessageHandler(ButtonDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	@Override
	public void handle(Player player, ExtendedButtonMessage message) {
		dispatcher.handle(player, message.getId(), message.getSlot(), message.getParameter());
	}

}
