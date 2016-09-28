package com.runescape.game.msg.codec.decoder;

import java.io.IOException;

import com.runescape.game.msg.FlagsMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameReader;

public final class FlagsMessageDecoder extends MessageDecoder<FlagsMessage> {

	public FlagsMessageDecoder() {
		super(98);
	}

	@Override
	public FlagsMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int flags = (int) reader.getUnsigned(DataType.INT);
		return new FlagsMessage(flags);
	}

}
