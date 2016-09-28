package com.runescape.game.msg.codec.decoder;

import java.io.IOException;

import com.runescape.game.msg.DisplayMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameReader;

public final class DisplayMessageDecoder extends MessageDecoder<DisplayMessage> {

	public DisplayMessageDecoder() {
		super(Opcode.DISPLAY_MESSAGE);
	}

	@Override
	public DisplayMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int mode = (int) reader.getUnsigned(DataType.BYTE);
		int width = (int) reader.getUnsigned(DataType.SHORT);
		int height = (int) reader.getUnsigned(DataType.SHORT);
		reader.getUnsigned(DataType.BYTE); // TODO identify this
		return new DisplayMessage(mode, width, height);
	}

}
