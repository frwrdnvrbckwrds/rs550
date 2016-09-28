package com.runescape.game.msg.codec.encoder;

import com.runescape.game.msg.InterfaceCloseMessage;
import com.runescape.game.msg.codec.MessageEncoder;
import com.runescape.game.net.game.DataOrder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameBuilder;

import io.netty.buffer.ByteBufAllocator;

public final class InterfaceCloseMessageEncoder extends MessageEncoder<InterfaceCloseMessage> {

	public InterfaceCloseMessageEncoder() {
		super(InterfaceCloseMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, InterfaceCloseMessage message) {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.INTERFACE_CLOSE);
		builder.put(DataType.SHORT, 0);
		//builder.put(DataType.SHORT, message.getId());
		//builder.put(DataType.SHORT, message.getSlot());
		builder.put(DataType.INT, DataOrder.INVERSED_MIDDLE, (message.getId() << 16) | message.getSlot());
		return builder.toGameFrame();
	}

}
