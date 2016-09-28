package com.runescape.game.msg.codec;

import java.io.IOException;

import com.runescape.game.msg.Message;
import com.runescape.game.net.game.GameFrame;

public abstract class MessageDecoder<T extends Message> {

	protected final int opcode;

	public MessageDecoder(int opcode) {
		this.opcode = opcode;
	}

	public abstract T decode(GameFrame frame) throws IOException;

}
