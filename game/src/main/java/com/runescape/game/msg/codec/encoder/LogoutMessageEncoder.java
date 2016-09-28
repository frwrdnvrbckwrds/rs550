package com.runescape.game.msg.codec.encoder;

import com.runescape.game.msg.LogoutMessage;
import com.runescape.game.msg.codec.MessageEncoder;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameBuilder;

import io.netty.buffer.ByteBufAllocator;

public final class LogoutMessageEncoder extends MessageEncoder<LogoutMessage> {

	public LogoutMessageEncoder() {
		super(LogoutMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, LogoutMessage message) {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.LOGOUT);
		return builder.toGameFrame();
	}

}
