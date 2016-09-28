package com.runescape.game.msg.codec.decoder;

import java.io.IOException;

import com.runescape.game.msg.InterfaceClosedMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.GameFrame;

public final class InterfaceClosedMessageDecoder extends MessageDecoder<InterfaceClosedMessage> {

	private static final InterfaceClosedMessage INTERFACE_CLOSED_MESSAGE = new InterfaceClosedMessage();

	public InterfaceClosedMessageDecoder() {
		super(Opcode.INTERFACE_CLOSED); //184
	}

	@Override
	public InterfaceClosedMessage decode(GameFrame frame) throws IOException {
		return INTERFACE_CLOSED_MESSAGE;
	}

}
