package com.runescape.game.msg.codec.encoder;

import com.runescape.game.msg.SkillMessage;
import com.runescape.game.msg.codec.MessageEncoder;
import com.runescape.game.net.game.*;

import io.netty.buffer.ByteBufAllocator;

public final class SkillMessageEncoder extends MessageEncoder<SkillMessage> {

	public SkillMessageEncoder() {
		super(SkillMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, SkillMessage message) {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, 164);
		builder.put(DataType.BYTE, message.getLevel());
		builder.put(DataType.INT, DataOrder.MIDDLE, message.getExperience());
		builder.put(DataType.BYTE, message.getSkill());
		return builder.toGameFrame();
	}

}
