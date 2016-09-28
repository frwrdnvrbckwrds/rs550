package com.runescape.game.msg.codec.encoder;

import java.io.IOException;

import com.runescape.game.msg.MusicMessage;
import com.runescape.game.msg.codec.MessageEncoder;
import com.runescape.game.net.game.DataTransformation;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameBuilder;

import io.netty.buffer.ByteBufAllocator;

public class TempMusicMessageEncoder extends MessageEncoder<MusicMessage> {

	public TempMusicMessageEncoder() {
		super(MusicMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, MusicMessage message) throws IOException {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.TEMP_MUSIC);
		
		builder.put(DataType.SHORT, message.getId());
		builder.put(DataType.BYTE, DataTransformation.ADD, message.getVolume());
		
		return builder.toGameFrame();
	}
}
