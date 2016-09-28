package com.runescape.game.msg.codec.decoder;

import java.io.IOException;

import com.runescape.game.msg.PingMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.GameFrame;

public final class PingMessageDecoder extends MessageDecoder<PingMessage> {

	private static final PingMessage PING_MESSAGE = new PingMessage();

	public PingMessageDecoder() {
		super(Opcode.PING);
	}

	@Override
	public PingMessage decode(GameFrame frame) throws IOException {
		return PING_MESSAGE;
	}

}
