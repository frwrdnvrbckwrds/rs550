package com.runescape.game.msg.codec.decoder;

import java.io.IOException;

import com.runescape.game.msg.WalkMessage;
import com.runescape.game.msg.WalkMessage.Step;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.DataOrder;
import com.runescape.game.net.game.DataTransformation;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameReader;

public final class WalkMessageDecoder extends MessageDecoder<WalkMessage> {

	public WalkMessageDecoder(int opcode) {
		super(opcode);
	}

	@Override
	public WalkMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);

        int minimapFlagId = (int) reader.getUnsigned(DataType.BYTE, DataTransformation.SUBTRACT);
        boolean running = reader.getUnsigned(DataType.BYTE) == 1;
        int y = (int) reader.getUnsigned(DataType.SHORT);
        int x = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD);
        return new WalkMessage(new Step(x, y), running, minimapFlagId);
	}
}
