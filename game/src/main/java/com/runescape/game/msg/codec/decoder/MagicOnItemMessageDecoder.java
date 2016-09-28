package com.runescape.game.msg.codec.decoder;

import java.io.IOException;

import com.runescape.game.msg.MagicOnItemMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.DataOrder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameReader;

public class MagicOnItemMessageDecoder extends MessageDecoder<MagicOnItemMessage> {

	public MagicOnItemMessageDecoder() {
		super(Opcode.MAGIC_ON_ITEM);
	}

	@Override
	public MagicOnItemMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int spellBook = (int) reader.getUnsigned(DataType.INT, DataOrder.INVERSED_MIDDLE);
		int itemId = (int) reader.getUnsigned(DataType.SHORT);
		int spellId = (int) reader.getSigned(DataType.INT, DataOrder.INVERSED_MIDDLE);
		int itemSlotId = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE);
		int params = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE);
		return new MagicOnItemMessage(itemId, itemSlotId, spellId, spellBook);
	}

}
