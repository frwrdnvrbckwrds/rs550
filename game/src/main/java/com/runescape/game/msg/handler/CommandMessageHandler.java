package com.runescape.game.msg.handler;

import com.runescape.game.command.CommandDispatcher;
import com.runescape.game.model.player.Player;
import com.runescape.game.msg.CommandMessage;

public final class CommandMessageHandler extends MessageHandler<CommandMessage> {

	private final CommandDispatcher dispatcher;

    public CommandMessageHandler(CommandDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
	public void handle(Player player, CommandMessage message) {
		dispatcher.handle(player, message.getCommand());
	}

}