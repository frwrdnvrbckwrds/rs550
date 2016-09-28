package com.runescape.game.net.game;

import io.netty.buffer.MessageBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runescape.game.msg.Message;
import com.runescape.game.msg.codec.CodecRepository;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.msg.codec.decoder.Opcode;

import java.io.IOException;

public final class GameMessageDecoder extends MessageToMessageDecoder<GameFrame> {

	private static final Logger logger = LoggerFactory.getLogger(GameMessageDecoder.class);

	private final CodecRepository codecs;

	public GameMessageDecoder(CodecRepository codecs) {
		this.codecs = codecs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void decode(ChannelHandlerContext ctx, GameFrame frame, MessageBuf<Object> out) throws IOException {
		if (frame.getOpcode() != Opcode.PING 
				&& frame.getOpcode() != Opcode.CLICK 
				&& frame.getOpcode() != Opcode.CAMERA) {
			System.out.println("Received opcode:" + frame.getOpcode());
		}
		
		MessageDecoder<Message> decoder = (MessageDecoder<Message>) codecs.get(frame.getOpcode());

		if (decoder == null) {
			logger.warn("No decoder for packet id " + frame.getOpcode() + ".");
			return;
		}

		out.add(decoder.decode(frame));
	}

}
