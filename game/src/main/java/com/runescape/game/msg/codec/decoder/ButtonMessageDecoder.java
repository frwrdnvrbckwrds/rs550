package com.runescape.game.msg.codec.decoder;

import com.runescape.game.msg.ButtonMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameReader;

public final class ButtonMessageDecoder extends MessageDecoder<ButtonMessage> {

	public ButtonMessageDecoder() {
		super(Opcode.BUTTON);
	}

	@Override
	public ButtonMessage decode(GameFrame frame) {
		GameFrameReader reader = new GameFrameReader(frame);
		int button = (int) reader.getSigned(DataType.INT);
		int id = (button >> 16) & 0xFFFF;
		int slot = button & 0xFFFF;
		return new ButtonMessage(id, slot);
	}

}
