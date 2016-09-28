package com.runescape.game.msg.codec.encoder;

import java.io.IOException;

import com.runescape.game.msg.FriendsStatusMessage;
import com.runescape.game.msg.codec.MessageEncoder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameBuilder;

import io.netty.buffer.ByteBufAllocator;

public class FriendsStatusMessageEncoder extends MessageEncoder<FriendsStatusMessage> {

	public FriendsStatusMessageEncoder() {
		super(FriendsStatusMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, FriendsStatusMessage message) throws IOException {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.FRIENDS_STATUS);
		builder.put(DataType.BYTE, message.getType());
		return builder.toGameFrame();
	}

}
