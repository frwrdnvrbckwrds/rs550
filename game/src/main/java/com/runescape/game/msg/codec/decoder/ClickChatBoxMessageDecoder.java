package com.runescape.game.msg.codec.decoder;

import java.io.IOException;

import com.runescape.game.msg.ClickChatBoxMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameReader;

public class ClickChatBoxMessageDecoder extends MessageDecoder<ClickChatBoxMessage> {

	public ClickChatBoxMessageDecoder(int opcode) {
		super(opcode);
	}

	@Override
	public ClickChatBoxMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int localPlayerId = (int) reader.getUnsigned(DataType.SHORT);
		int type = (int) reader.getUnsigned(DataType.BYTE);
		return new ClickChatBoxMessage(type, localPlayerId);
	}

}
