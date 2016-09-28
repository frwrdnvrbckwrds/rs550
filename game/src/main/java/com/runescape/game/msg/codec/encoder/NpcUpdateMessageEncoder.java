package com.runescape.game.msg.codec.encoder;

import io.netty.buffer.ByteBufAllocator;

import java.io.IOException;

import com.runescape.game.msg.NpcUpdateMessage;
import com.runescape.game.msg.codec.MessageEncoder;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameBuilder;
import com.runescape.game.update.NpcDescriptor;

public final class NpcUpdateMessageEncoder extends MessageEncoder<NpcUpdateMessage> {

	public NpcUpdateMessageEncoder() {
		super(NpcUpdateMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, NpcUpdateMessage message) throws IOException {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.NPC_UPDATE, GameFrame.Type.VARIABLE_SHORT);
		GameFrameBuilder blockBuilder = new GameFrameBuilder(alloc);
		builder.switchToBitAccess();

		builder.putBits(8, message.getLocalNpcCount());

		for (NpcDescriptor descriptor : message.getDescriptors())
			descriptor.encode(message, builder, blockBuilder);

		if (blockBuilder.getLength() > 0) {
			builder.putBits(15, 32767);
			builder.switchToByteAccess();
			builder.putRawBuilder(blockBuilder);
		} else {
			builder.switchToByteAccess();
		}

		return builder.toGameFrame();
	}

}
