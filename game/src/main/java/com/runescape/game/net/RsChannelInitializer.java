package com.runescape.game.net;

import com.runescape.game.GameServer;
import com.runescape.game.net.handshake.HandshakeDecoder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

public final class RsChannelInitializer extends ChannelInitializer<SocketChannel> {

	private final GameServer server;

	public RsChannelInitializer(GameServer server) {
		this.server = server;
	}

	@Override
	public void initChannel(SocketChannel ch) {
		ch.pipeline().addLast(
			new ReadTimeoutHandler(5),
			new HandshakeDecoder(),
			new RsChannelHandler(server));
	}

}
