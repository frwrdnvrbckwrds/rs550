package com.runescape.game.msg.codec.decoder;

import java.io.IOException;

import com.runescape.game.msg.EquipItemMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.*;

public final class EquipItemMessageDecoder extends MessageDecoder<EquipItemMessage> {

	public EquipItemMessageDecoder() {
		super(Opcode.WEAR_ITEM);
	}

	@Override
	public EquipItemMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int slot = (int) reader.getUnsigned(DataType.SHORT);
		int interfaceId = (int) reader.getUnsigned(DataType.SHORT);
		int itemFlag = (int) reader.getSigned(DataType.INT, DataOrder.MIDDLE);
		int itemId = (itemFlag >> 16) & 0xFFFF; 
		int inventSlot = (itemFlag & 0xFFFF) / 256;
		return new EquipItemMessage(interfaceId, inventSlot, itemId, slot);
	}

}
