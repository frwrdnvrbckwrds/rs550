package com.runescape.game.msg.codec.encoder;

import java.io.IOException;

import com.runescape.game.model.World;
import com.runescape.game.model.friends.FriendStatus;
import com.runescape.game.model.player.Player;
import com.runescape.game.msg.UpdateFriendMessage;
import com.runescape.game.msg.codec.MessageEncoder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameBuilder;
import com.runescape.game.net.game.GameFrame.Type;
import com.runescape.game.util.Utils;

import io.netty.buffer.ByteBufAllocator;

public class UpdateFriendsMessageEncoder extends MessageEncoder<UpdateFriendMessage> {

	public UpdateFriendsMessageEncoder() {
		super(UpdateFriendMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, UpdateFriendMessage message) throws IOException {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.UPDATE_FRIEND, Type.VARIABLE_BYTE);
		
		Player target = World.getWorld().getPlayerByName(message.getName());
		FriendStatus status = FriendStatus.OFFLINE;
		
		if (target != null) {
			status = target.getFriends().getStatus();
		}
		
		if (status == FriendStatus.FRIENDS_ONLY) {
			if (message.getPlayer().getFriends().containsFriend(target) && target.getFriends().containsFriend(message.getPlayer())) {
				if (status != FriendStatus.OFFLINE)
					status = FriendStatus.ONLINE;
			}
		}
		
		builder.put(DataType.LONG, Utils.stringToLong(message.getName())); //name
		builder.put(DataType.SHORT, status.getType()); //status
		builder.put(DataType.BYTE, 2); //World
		builder.putString("World 1"); //world
		
		return builder.toGameFrame();
	}

}
