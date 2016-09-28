package com.runescape.game.net.world;

import com.runescape.game.GameServer;
import com.runescape.game.net.Session;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;

public final class WorldListSession extends Session {

	private static final Country[] COUNTRIES = {
		new Country(Country.FLAG_USA, "USA 1")
	};

	public WorldListSession(GameServer server, Channel channel) {
		super(server, channel);
	}

	@Override
	public void messageReceived(Object message) {
		World[] worlds = { 
				new World(1, 0, 0, "Trade - F2P", "127.0.0.1")
		};
		int[] players = { com.runescape.game.model.World.getWorld().getPlayers().size() };
		channel.write(new WorldListMessage(0xDEADBEEF, COUNTRIES, worlds, players)).addListener(ChannelFutureListener.CLOSE);
	}

}