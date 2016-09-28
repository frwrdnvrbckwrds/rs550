package com.runescape.game.msg.codec.decoder;

import java.io.IOException;

import com.runescape.game.msg.DropItemMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.DataOrder;
import com.runescape.game.net.game.DataTransformation;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameReader;

public class DropItemMessageDecoder extends MessageDecoder<DropItemMessage> {

	public DropItemMessageDecoder() {
		super(Opcode.DROP_ITEM);
	}

	@Override
	public DropItemMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int itemId = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD);
		int interfaceId = (int) reader.getUnsigned(DataType.INT, DataOrder.INVERSED_MIDDLE) / 256;
		int slotId = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD);
		return new DropItemMessage(itemId, interfaceId, slotId);
	}

}
