package com.runescape.game.msg.codec.encoder;

import io.netty.buffer.ByteBufAllocator;

import java.io.IOException;

import com.runescape.game.msg.InterfaceResetItemsMessage;
import com.runescape.game.msg.codec.MessageEncoder;
import com.runescape.game.net.game.DataOrder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameBuilder;

public final class InterfaceResetItemsMessageEncoder extends MessageEncoder<InterfaceResetItemsMessage> {

	public InterfaceResetItemsMessageEncoder() {
		super(InterfaceResetItemsMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, InterfaceResetItemsMessage message) throws IOException {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.INTERFACE_RESET_ITEMS);
		builder.put(DataType.INT, DataOrder.MIDDLE, (message.getId() << 16) | message.getSlot());
		return builder.toGameFrame();
	}

}
