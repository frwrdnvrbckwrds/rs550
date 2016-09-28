package com.runescape.game.msg.codec.decoder;

import net.scapeemulator.util.ChatUtils;

import java.io.IOException;

import com.runescape.game.msg.ChatMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameReader;

public final class ChatMessageDecoder extends MessageDecoder<ChatMessage> {

	public ChatMessageDecoder() {
		super(Opcode.CHAT_OPCODE);
	}

	@Override
	public ChatMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int size = reader.getLength() - 2;

		int color = (int) reader.getUnsigned(DataType.BYTE);
		int effects = (int) reader.getUnsigned(DataType.BYTE);

		byte[] bytes = new byte[size];
		reader.getBytes(bytes);
		String text = ChatUtils.unpack(bytes);

		return new ChatMessage(color, effects, text);
	}

}
