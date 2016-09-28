package com.runescape.game.msg.handler;

import com.runescape.game.button.ButtonDispatcher;
import com.runescape.game.model.player.Player;
import com.runescape.game.msg.ButtonMessage;

public final class ButtonMessageHandler extends MessageHandler<ButtonMessage> {

	private final ButtonDispatcher dispatcher;

	public ButtonMessageHandler(ButtonDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	@Override
	public void handle(Player player, ButtonMessage message) {
		dispatcher.handle(player, message.getId(), message.getSlot(), 0);
	}

}
