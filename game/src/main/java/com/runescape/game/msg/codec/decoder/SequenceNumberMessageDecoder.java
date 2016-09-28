package com.runescape.game.msg.codec.decoder;

import java.io.IOException;

import com.runescape.game.msg.SequenceNumberMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameReader;

public final class SequenceNumberMessageDecoder extends MessageDecoder<SequenceNumberMessage> {

	public SequenceNumberMessageDecoder() {
		super(177);
	}

	@Override
	public SequenceNumberMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int sequenceNumber = (int) reader.getUnsigned(DataType.SHORT);
		return new SequenceNumberMessage(sequenceNumber);
	}

}
