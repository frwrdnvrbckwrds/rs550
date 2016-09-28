package com.runescape.game.msg.codec.decoder;

import java.io.IOException;

import com.runescape.game.msg.FocusMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameReader;

public final class FocusMessageDecoder extends MessageDecoder<FocusMessage> {

	private static final FocusMessage FOCUSED_MESSAGE = new FocusMessage(true);
	private static final FocusMessage NOT_FOCUSED_MESSAGE = new FocusMessage(false);

	public FocusMessageDecoder() {
		super(Opcode.FOCUS_MESSAGE);
	}

	@Override
	public FocusMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int focused = (int) reader.getUnsigned(DataType.BYTE);
		return focused != 0 ? FOCUSED_MESSAGE : NOT_FOCUSED_MESSAGE;
	}

}
