package com.runescape.game.msg.codec.decoder;

import java.io.IOException;

import com.runescape.game.msg.ExtendedButtonMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameReader;

public final class ExtendedButtonMessageDecoder extends MessageDecoder<ExtendedButtonMessage> {

	public ExtendedButtonMessageDecoder() {
		super(Opcode.EXTENDED_BUTTON);
	}

	@Override
	public ExtendedButtonMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int button = (int) reader.getSigned(DataType.INT);
		int id = (button >> 16) & 0xFFFF;
		int slot = button & 0xFFFF;
		int parameter = (int) reader.getUnsigned(DataType.SHORT);
		
		System.out.println("id:" + id + " slot:" + slot + " params:" + parameter);
		
		return new ExtendedButtonMessage(id, slot, parameter);
	}

}
