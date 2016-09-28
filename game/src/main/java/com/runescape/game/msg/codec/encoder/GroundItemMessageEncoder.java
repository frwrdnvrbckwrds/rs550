package com.runescape.game.msg.codec.encoder;

import java.io.IOException;

import com.runescape.game.msg.GroundItemMessage;
import com.runescape.game.msg.codec.MessageEncoder;
import com.runescape.game.net.game.DataOrder;
import com.runescape.game.net.game.DataTransformation;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameBuilder;

import io.netty.buffer.ByteBufAllocator;

public class GroundItemMessageEncoder extends MessageEncoder<GroundItemMessage> {

	public GroundItemMessageEncoder() {
		super(GroundItemMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, GroundItemMessage message) throws IOException {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.CREATE_GROUND_ITEM);
		
		builder.put(DataType.BYTE, DataTransformation.ADD, message.getPositon().blockHash());
		builder.put(DataType.SHORT, DataOrder.LITTLE, message.getItem().getId());
		builder.put(DataType.SHORT, DataTransformation.ADD, message.getItem().getAmount());
		
		return builder.toGameFrame();
	}


}
