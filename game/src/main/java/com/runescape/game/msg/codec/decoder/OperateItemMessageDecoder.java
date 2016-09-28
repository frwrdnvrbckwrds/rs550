package com.runescape.game.msg.codec.decoder;

import java.io.IOException;

import com.runescape.game.model.Interface;
import com.runescape.game.msg.OperateItemMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.DataOrder;
import com.runescape.game.net.game.DataTransformation;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameReader;

public class OperateItemMessageDecoder extends MessageDecoder<OperateItemMessage> {

	public OperateItemMessageDecoder() {
		super(Opcode.OPERATE_ITEM);
	}

	@Override
	public OperateItemMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		
		int slot = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD);
		int itemId = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD);
		int unknown = (int) reader.getSigned(DataType.INT, DataOrder.INVERSED_MIDDLE);
		
		return new OperateItemMessage(slot, itemId);
	}

}
