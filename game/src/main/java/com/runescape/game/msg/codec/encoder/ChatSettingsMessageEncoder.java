package com.runescape.game.msg.codec.encoder;

import java.io.IOException;

import com.runescape.game.msg.ChatSettingMessage;
import com.runescape.game.msg.codec.MessageEncoder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameBuilder;

import io.netty.buffer.ByteBufAllocator;

public class ChatSettingsMessageEncoder extends MessageEncoder<ChatSettingMessage> {

	public ChatSettingsMessageEncoder() {
		super(ChatSettingMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, ChatSettingMessage message) throws IOException {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, 23);
		builder.put(DataType.BYTE, message.getFriends());
		builder.put(DataType.BYTE, message.getClan());
		builder.put(DataType.BYTE, message.getTrade());
		return builder.toGameFrame();
	}

}
