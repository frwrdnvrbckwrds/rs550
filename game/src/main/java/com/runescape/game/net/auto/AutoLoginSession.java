package com.runescape.game.net.auto;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;

import java.io.IOException;

import com.runescape.game.GameServer;
import com.runescape.game.net.Session;
import com.runescape.game.net.login.LoginResponse;

public final class AutoLoginSession extends Session {

	public AutoLoginSession(GameServer server, Channel channel) {
		super(server, channel);
	}

	@Override
	public void messageReceived(Object message) throws IOException {
		ByteBuf payload = channel.alloc().buffer(2);
		payload.writeShort(1);
		LoginResponse response = new LoginResponse(LoginResponse.STATUS_SWITCH_WORLD_AND_RETRY, payload);
		channel.write(response).addListener(ChannelFutureListener.CLOSE);
	}

}
