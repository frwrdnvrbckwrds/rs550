package com.runescape.game.msg.codec.encoder;

import java.io.IOException;

import com.runescape.game.msg.PlayerOptionMessage;
import com.runescape.game.msg.codec.MessageEncoder;
import com.runescape.game.net.game.DataOrder;
import com.runescape.game.net.game.DataTransformation;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameBuilder;

import io.netty.buffer.ByteBufAllocator;

public class PlayerOptionMessageEncoder extends MessageEncoder<PlayerOptionMessage> {

	public PlayerOptionMessageEncoder() {
		super(PlayerOptionMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, PlayerOptionMessage message) throws IOException {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, 126);
		builder.putString(message.getOption());
		builder.put(DataType.BYTE, DataTransformation.NEGATE, 1);
		builder.put(DataType.SHORT, DataOrder.LITTLE, 1);
		builder.put(DataType.BYTE, DataTransformation.SUBTRACT, 1);
		return builder.toGameFrame();
	}

}
