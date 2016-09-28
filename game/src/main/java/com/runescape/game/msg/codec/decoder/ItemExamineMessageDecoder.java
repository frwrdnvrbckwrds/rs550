package com.runescape.game.msg.codec.decoder;

import java.io.IOException;

import com.runescape.game.msg.ItemExamineMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameReader;

public class ItemExamineMessageDecoder extends MessageDecoder<ItemExamineMessage> {

	public ItemExamineMessageDecoder() {
		super(Opcode.EXAMINE_ITEM);
	}

	@Override
	public ItemExamineMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int id = (int) reader.getUnsigned(DataType.SHORT);
		return new ItemExamineMessage(id);
	}

}
