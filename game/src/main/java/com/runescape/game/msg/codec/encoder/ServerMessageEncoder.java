package com.runescape.game.msg.codec.encoder;

import com.runescape.game.msg.ServerMessage;
import com.runescape.game.msg.codec.MessageEncoder;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameBuilder;
import com.runescape.game.net.game.GameFrame.Type;

import io.netty.buffer.ByteBufAllocator;

public final class ServerMessageEncoder extends MessageEncoder<ServerMessage> {

	public ServerMessageEncoder() {
		super(ServerMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, ServerMessage message) {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.SERVER_MESSAGE, Type.VARIABLE_BYTE);
		builder.putString(message.getText());
		return builder.toGameFrame();
	}

}
