package com.runescape.game.msg.codec.encoder;

import io.netty.buffer.ByteBufAllocator;

import java.io.IOException;

import com.runescape.game.msg.EnergyMessage;
import com.runescape.game.msg.codec.MessageEncoder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameBuilder;

public final class EnergyMessageEncoder extends MessageEncoder<EnergyMessage> {

	public EnergyMessageEncoder() {
		super(EnergyMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, EnergyMessage message) throws IOException {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.ENERGY);
		builder.put(DataType.BYTE, message.getEnergy());
		return builder.toGameFrame();
	}

}
