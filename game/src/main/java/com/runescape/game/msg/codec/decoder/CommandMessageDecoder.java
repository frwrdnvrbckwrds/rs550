package com.runescape.game.msg.codec.decoder;

import java.io.IOException;

import com.runescape.game.msg.CommandMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameReader;

public final class CommandMessageDecoder extends MessageDecoder<CommandMessage> {

	public CommandMessageDecoder() {
		super(Opcode.COMMAND);
	}

	@Override
	public CommandMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		String command = reader.getString();
		return new CommandMessage(command);
	}

}
