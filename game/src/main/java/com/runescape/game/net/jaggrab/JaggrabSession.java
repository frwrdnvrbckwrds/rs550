package com.runescape.game.net.jaggrab;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.FileRegion;

import java.io.IOException;

import com.runescape.game.GameServer;
import com.runescape.game.net.Session;
import com.runescape.game.net.file.FileProvider;

public final class JaggrabSession extends Session {

	private static final FileProvider provider = new FileProvider(true);

	public JaggrabSession(GameServer server, Channel channel) {
		super(server, channel);
	}

	@Override
	public void messageReceived(Object message) throws IOException {
		JaggrabRequest request = (JaggrabRequest) message;
        System.out.println("JAG: " + request.getPath());

		FileRegion file = provider.serve(request.getPath());
		if (file != null) {
			channel.write(file).addListener(ChannelFutureListener.CLOSE);
		} else {
			channel.close();
		}
	}

}
