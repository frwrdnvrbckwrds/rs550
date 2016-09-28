package com.runescape.game.net.game;

import io.netty.buffer.MessageBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.io.IOException;

import com.runescape.game.msg.Message;
import com.runescape.game.msg.codec.CodecRepository;
import com.runescape.game.msg.codec.MessageEncoder;

public final class GameMessageEncoder extends MessageToMessageEncoder<Message> {

	private final CodecRepository codecs;

	public GameMessageEncoder(CodecRepository codecs) {
		this.codecs = codecs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void encode(ChannelHandlerContext ctx, Message message, MessageBuf<Object> out) throws IOException {
		MessageEncoder<Message> encoder = (MessageEncoder<Message>) codecs.get(message.getClass());
		out.add(encoder.encode(ctx.alloc(), message));
	}

}
