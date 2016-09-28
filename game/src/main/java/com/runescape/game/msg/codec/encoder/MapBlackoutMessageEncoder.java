package com.runescape.game.msg.codec.encoder;

import java.io.IOException;

import com.runescape.game.msg.MapBlackoutMessage;
import com.runescape.game.msg.codec.MessageEncoder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameBuilder;

import io.netty.buffer.ByteBufAllocator;

public class MapBlackoutMessageEncoder extends MessageEncoder<MapBlackoutMessage> {

	public MapBlackoutMessageEncoder() {
		super(MapBlackoutMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, MapBlackoutMessage message) throws IOException {
		final GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.MAP_BLACK_OUT);
		builder.put(DataType.BYTE, message.getStatus());
		return builder.toGameFrame();
	}

}
