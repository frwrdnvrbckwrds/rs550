package com.runescape.game.msg.codec.encoder;

import io.netty.buffer.ByteBufAllocator;

import java.io.IOException;

import com.runescape.game.msg.ResetMinimapFlagMessage;
import com.runescape.game.msg.codec.MessageEncoder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameBuilder;

public final class ResetMinimapFlagMessageEncoder extends MessageEncoder<ResetMinimapFlagMessage> {

	public ResetMinimapFlagMessageEncoder() {
		super(ResetMinimapFlagMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, ResetMinimapFlagMessage message) throws IOException {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, 33);
        builder.put(DataType.BYTE, 0xFF);
        builder.put(DataType.BYTE, 0);
        builder.put(DataType.BYTE, message.getFlagId());
		return builder.toGameFrame();
	}

}
