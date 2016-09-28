package com.runescape.game.msg.codec.decoder;

import java.io.IOException;

import com.runescape.game.msg.ItemOnObjectMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.DataOrder;
import com.runescape.game.net.game.DataTransformation;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameReader;

public class ItemOnObjectMessageDecoder extends MessageDecoder<ItemOnObjectMessage> {

	public ItemOnObjectMessageDecoder() {
		super(Opcode.ITEM_ON_OBJECT);
	}

	@Override
	public ItemOnObjectMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int slotId = (int) reader.getUnsigned(DataType.SHORT);
		int objectX = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD);
		int objectId = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD);
		int interfaceId = (int) reader.getSigned(DataType.INT, DataOrder.INVERSED_MIDDLE);
		int unknown = (int) reader.getUnsigned(DataType.BYTE, DataTransformation.NEGATE);
		int objectY = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE);
		int itemId = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE);
		return new ItemOnObjectMessage(objectId, objectX, objectY, itemId, slotId, interfaceId);
	}

}
