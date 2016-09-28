package com.runescape.game.msg.codec.decoder;

import com.runescape.game.msg.ObjectOptionTwoMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.*;

public final class ObjectOptionTwoMessageDecoder extends MessageDecoder<ObjectOptionTwoMessage> {

	public ObjectOptionTwoMessageDecoder() {
		super(194);
	}

	@Override
	public ObjectOptionTwoMessage decode(GameFrame frame) {
		GameFrameReader reader = new GameFrameReader(frame);
		int y = (int) reader.getSigned(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD);
		int x = (int) reader.getSigned(DataType.SHORT, DataOrder.LITTLE);
		int id = (int) reader.getSigned(DataType.SHORT);
		return new ObjectOptionTwoMessage(x, y, id);
	}

}
