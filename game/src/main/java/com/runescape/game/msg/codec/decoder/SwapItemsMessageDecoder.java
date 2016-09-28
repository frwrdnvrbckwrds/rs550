package com.runescape.game.msg.codec.decoder;

import java.io.IOException;

import com.runescape.game.msg.SwapItemsMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.*;

public final class SwapItemsMessageDecoder extends MessageDecoder<SwapItemsMessage> {

	public SwapItemsMessageDecoder() {
		super(231);
	}

	@Override
	public SwapItemsMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int source = (int) reader.getUnsigned(DataType.SHORT);
		int inter = (int) reader.getSigned(DataType.INT, DataOrder.LITTLE);
		int id = (inter >> 16) & 0xFFFF;
		int slot = inter & 0xFFFF;
		int destination = (int) reader.getUnsigned(DataType.SHORT, DataTransformation.ADD);
		int type = (int) reader.getUnsigned(DataType.BYTE, DataTransformation.SUBTRACT);
		return new SwapItemsMessage(id, slot, source, destination, type);
	}

}
