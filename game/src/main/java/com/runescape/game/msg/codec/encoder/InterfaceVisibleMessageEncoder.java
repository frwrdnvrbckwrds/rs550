package com.runescape.game.msg.codec.encoder;

import com.runescape.game.msg.InterfaceVisibleMessage;
import com.runescape.game.msg.codec.MessageEncoder;
import com.runescape.game.net.game.*;

import io.netty.buffer.ByteBufAllocator;

public final class InterfaceVisibleMessageEncoder extends MessageEncoder<InterfaceVisibleMessage> {

	public InterfaceVisibleMessageEncoder() {
		super(InterfaceVisibleMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, InterfaceVisibleMessage message) {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.INTERFACE_VISIBLE);
		builder.put(DataType.BYTE, DataTransformation.NEGATE, message.isVisible() ? 0 : 1);
		builder.put(DataType.SHORT, 0);
		builder.put(DataType.INT, DataOrder.LITTLE, (message.getId() << 16) | message.getSlot());
		return builder.toGameFrame();
	}

}
