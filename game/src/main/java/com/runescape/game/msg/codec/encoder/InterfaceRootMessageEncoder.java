package com.runescape.game.msg.codec.encoder;

import com.runescape.game.msg.InterfaceRootMessage;
import com.runescape.game.msg.codec.MessageEncoder;
import com.runescape.game.net.game.*;
import com.runescape.game.net.game.GameFrame.Type;

import io.netty.buffer.ByteBufAllocator;

public final class InterfaceRootMessageEncoder extends MessageEncoder<InterfaceRootMessage> {

	public InterfaceRootMessageEncoder() {
		super(InterfaceRootMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, InterfaceRootMessage message) {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.INTERFACE_ROOT_MESSAGE, Type.FIXED);
		builder.put(DataType.SHORT, message.getId());
        builder.put(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD, 0);
		builder.put(DataType.BYTE, DataTransformation.SUBTRACT, 0);
		return builder.toGameFrame();
	}

}
