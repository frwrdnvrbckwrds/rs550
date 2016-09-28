package com.runescape.game.msg.codec.encoder;

import io.netty.buffer.ByteBufAllocator;

import java.io.IOException;

import com.runescape.game.msg.ConfigMessage;
import com.runescape.game.msg.codec.MessageEncoder;
import com.runescape.game.net.game.DataOrder;
import com.runescape.game.net.game.DataTransformation;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameBuilder;

public final class ConfigMessageEncoder extends MessageEncoder<ConfigMessage> {

	public ConfigMessageEncoder() {
		super(ConfigMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, ConfigMessage message) throws IOException {
		int id = message.getId();
		int value = message.getValue();

		if (value < 128 && value > -128) {
			GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.CONFIG_MESSAGE);
			builder.put(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD, id);
			builder.put(DataType.INT, DataOrder.INVERSED_MIDDLE, value);
			return builder.toGameFrame();
		} else {
			GameFrameBuilder builder = new GameFrameBuilder(alloc, 10);
			builder.put(DataType.INT, DataOrder.INVERSED_MIDDLE, value);
			builder.put(DataType.SHORT, DataTransformation.ADD, id);
			return builder.toGameFrame();
		}
	}

}
