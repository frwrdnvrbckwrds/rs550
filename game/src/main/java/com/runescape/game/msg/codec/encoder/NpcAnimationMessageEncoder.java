package com.runescape.game.msg.codec.encoder;

import java.io.IOException;

import com.runescape.game.msg.NpcAnimationMessage;
import com.runescape.game.msg.codec.MessageEncoder;
import com.runescape.game.net.game.DataTransformation;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameBuilder;

import io.netty.buffer.ByteBufAllocator;

public class NpcAnimationMessageEncoder extends MessageEncoder<NpcAnimationMessage>{

	public NpcAnimationMessageEncoder() {
		super(NpcAnimationMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, NpcAnimationMessage message) throws IOException {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.NPC_ANIMATION);
		builder.put(DataType.SHORT, message.getAnimationId());
		builder.put(DataType.SHORT, DataTransformation.ADD, message.getNpcIndex());
		builder.put(DataType.BYTE, DataTransformation.NEGATE, message.getDelay());
		return builder.toGameFrame();
	}

}
