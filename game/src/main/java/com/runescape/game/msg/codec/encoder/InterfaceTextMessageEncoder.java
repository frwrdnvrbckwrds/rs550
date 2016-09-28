package com.runescape.game.msg.codec.encoder;

import com.runescape.game.msg.InterfaceTextMessage;
import com.runescape.game.msg.codec.MessageEncoder;
import com.runescape.game.net.game.*;
import com.runescape.game.net.game.GameFrame.Type;

import io.netty.buffer.ByteBufAllocator;

public final class InterfaceTextMessageEncoder extends MessageEncoder<InterfaceTextMessage> {

	public InterfaceTextMessageEncoder() {
		super(InterfaceTextMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, InterfaceTextMessage message) {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.INTERFACE_TEXT, Type.VARIABLE_SHORT);
        builder.put(DataType.SHORT, DataOrder.LITTLE, 0);
        builder.putString(message.getText());
		builder.put(DataType.INT, DataOrder.MIDDLE, (message.getId() << 16) | message.getSlot());
		return builder.toGameFrame();
	}

}
