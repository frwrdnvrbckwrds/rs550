package com.runescape.game.msg.codec.encoder;

import com.runescape.game.msg.InterfaceOpenMessage;
import com.runescape.game.msg.codec.MessageEncoder;
import com.runescape.game.net.game.*;

import io.netty.buffer.ByteBufAllocator;

public final class InterfaceOpenMessageEncoder extends MessageEncoder<InterfaceOpenMessage> {

	public InterfaceOpenMessageEncoder() {
		super(InterfaceOpenMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, InterfaceOpenMessage message) {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.INTERFACE_OPEN);
		builder.put(DataType.BYTE, DataTransformation.NEGATE, message.getType());
        builder.put(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD, 0);
		builder.put(DataType.INT, DataOrder.LITTLE, (message.getId() << 16) | message.getSlot());
		builder.put(DataType.SHORT, DataOrder.LITTLE, message.getChildId());
		return builder.toGameFrame();
	}

}
