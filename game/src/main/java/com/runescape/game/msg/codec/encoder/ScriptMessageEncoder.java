package com.runescape.game.msg.codec.encoder;

import io.netty.buffer.ByteBufAllocator;

import java.io.IOException;

import com.runescape.game.msg.ScriptMessage;
import com.runescape.game.msg.codec.MessageEncoder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameBuilder;
import com.runescape.game.net.game.GameFrame.Type;

public final class ScriptMessageEncoder extends MessageEncoder<ScriptMessage> {

	public ScriptMessageEncoder() {
		super(ScriptMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, ScriptMessage message) throws IOException {
		int id = message.getId();
		String types = message.getTypes();
		Object[] parameters = message.getParameters();

		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.SCRIPT_MESSAGE, Type.VARIABLE_SHORT); //70?
		builder.put(DataType.SHORT, 0);
		builder.putString(types);

		for (int i = types.length() - 1; i >= 0; i--) {
			if (types.charAt(i) == 's') {
				builder.putString((String) parameters[i]);
			} else {
				builder.put(DataType.INT, ((Number) parameters[i]).intValue());
			}
		}

		builder.put(DataType.INT, id);
		return builder.toGameFrame();
	}

}
