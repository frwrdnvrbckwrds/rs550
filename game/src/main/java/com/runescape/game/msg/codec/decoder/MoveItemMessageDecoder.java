package com.runescape.game.msg.codec.decoder;

import java.io.IOException;

import com.runescape.game.msg.MoveItemMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.DataOrder;
import com.runescape.game.net.game.DataTransformation;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameReader;

public class MoveItemMessageDecoder extends MessageDecoder<MoveItemMessage> {

	public MoveItemMessageDecoder() {
		super(Opcode.MOVE_ITEM);
	}

	@Override
	public MoveItemMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int newSlotId = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD);
		int unknown = (int) reader.getUnsigned(DataType.BYTE);
		int flag = (int) reader.getUnsigned(DataType.INT); 
		int interfaceId = ((flag >> 16) & 0xFFFF) / 256;
		int oldSlotId = (int) reader.getUnsigned(DataType.SHORT) / 256;
		return new MoveItemMessage(interfaceId, oldSlotId, newSlotId);
	}

}
