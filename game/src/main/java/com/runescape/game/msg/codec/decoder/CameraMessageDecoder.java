package com.runescape.game.msg.codec.decoder;

import java.io.IOException;

import com.runescape.game.msg.CameraMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.*;

public final class CameraMessageDecoder extends MessageDecoder<CameraMessage> {

	public CameraMessageDecoder() {
		super(Opcode.CAMERA);
	}

	@Override
	public CameraMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int pitch = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE);
		int yaw = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD);
		return new CameraMessage(yaw, pitch);
	}

}
