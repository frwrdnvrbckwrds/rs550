package com.runescape.game.msg.codec.decoder;

import java.io.IOException;

import com.runescape.game.msg.PrivateMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameReader;

public class PrivateMessageDecoder extends MessageDecoder<PrivateMessage> {

	public PrivateMessageDecoder() {
		super(Opcode.PRIVATE_MESSAGE);
	}

	@Override
	public PrivateMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int unknown = (int) reader.getUnsigned(DataType.BYTE);
		long message = reader.getSigned(DataType.LONG);
		return new PrivateMessage(message);
	}

}
