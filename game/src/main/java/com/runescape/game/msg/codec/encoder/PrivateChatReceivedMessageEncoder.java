package com.runescape.game.msg.codec.encoder;

import com.runescape.game.msg.PrivateChatReceivedMessage;
import com.runescape.game.msg.codec.MessageEncoder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameBuilder;
import com.runescape.game.net.game.GameFrame.Type;

import io.netty.buffer.ByteBufAllocator;

public final class PrivateChatReceivedMessageEncoder extends MessageEncoder<PrivateChatReceivedMessage> {

	public PrivateChatReceivedMessageEncoder() {
		super(PrivateChatReceivedMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, PrivateChatReceivedMessage message) {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, 0, Type.VARIABLE_BYTE);
		builder.put(DataType.LONG, message.getName());
		builder.put(DataType.SHORT, 0);
		int id = message.getMessageCounter();
		builder.putBytes(new byte[] { (byte) ((id << 16) & 0xFF), (byte) ((id << 8) & 0xFF), (byte) (id & 0xFF) });
		builder.put(DataType.BYTE, message.getRights());
		builder.putBytes(message.getPacked());
		return builder.toGameFrame();
	}
}