package com.runescape.game.net.game;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

import com.runescape.game.GameServer;
import com.runescape.game.model.Position;
import com.runescape.game.model.music.MusicPlayer;
import com.runescape.game.model.player.Player;
import com.runescape.game.model.region.Region;
import com.runescape.game.msg.FriendsStatusMessage;
import com.runescape.game.msg.Message;
import com.runescape.game.msg.MusicMessage;
import com.runescape.game.msg.RegionChangeMessage;
import com.runescape.game.msg.handler.MessageDispatcher;
import com.runescape.game.net.Session;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

public final class GameSession extends Session {

	private final Player player;
	private final MessageDispatcher dispatcher;
	private final Queue<Message> messages = new ArrayDeque<>();

	public GameSession(GameServer server, Channel channel, Player player) {
		super(server, channel);
		this.player = player;
		this.dispatcher = server.getMessageDispatcher();
	}

	public void init() {
		player.setSession(this);

		/* set up player for their initial region change */
		Position position = player.getPosition();
		player.setLastKnownRegion(position);
		player.send(new RegionChangeMessage(player.getPosition()));

		/* set up the game interface */
		player.getInterfaceSet().init();
		player.sendMessage("Welcome to RuneScape.");
		
		/* Play music */
		MusicPlayer.playMusic(player);
		
		/* refresh skills, inventory, energy, etc. */
		player.getInventory().refresh();
		player.getBank().refresh();
		player.getEquipment().refresh();
		player.getSettings().refresh();
		player.getSkillSet().refresh();
		player.setEnergy(player.getEnergy()); // TODO: nicer way than this?
		
		/* Friends list */
		player.send(new FriendsStatusMessage(2));
	}

	@Override
	public void messageReceived(Object message) throws IOException {
		synchronized (messages) {
			messages.add((Message) message);
		}
	}

	@Override
	public void channelClosed() {
		server.getLoginService().addLogoutRequest(player);
	}

	public ChannelFuture send(Message message) {
		return channel.write(message);
	}

	public void processMessageQueue() {
		synchronized (messages) {
			Message message;
			while ((message = messages.poll()) != null)
				dispatcher.dispatch(player, message);
		}
	}

}
