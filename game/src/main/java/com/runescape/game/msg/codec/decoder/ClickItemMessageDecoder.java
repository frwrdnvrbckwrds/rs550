package com.runescape.game.msg.codec.decoder;

import java.io.IOException;

import com.runescape.game.msg.ClickItemMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.DataOrder;
import com.runescape.game.net.game.DataTransformation;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameReader;

public class ClickItemMessageDecoder extends MessageDecoder<ClickItemMessage> {

	public ClickItemMessageDecoder() {
		super(Opcode.CLICK_ITEM);
	}

	@Override
	public ClickItemMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int slotId = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE,  DataTransformation.ADD);
		int itemId = (int) reader.getUnsigned(DataType.SHORT, DataTransformation.ADD);
		int flag = (int) reader.getSigned(DataType.INT);
		int interfaceId = (flag >> 16) & 0xFFFF;
		return new ClickItemMessage(itemId, interfaceId, slotId);
	}

}
