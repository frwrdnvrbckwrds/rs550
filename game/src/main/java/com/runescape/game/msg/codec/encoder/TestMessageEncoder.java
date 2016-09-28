package com.runescape.game.msg.codec.encoder;

import java.io.IOException;

import com.runescape.game.model.World;
import com.runescape.game.model.player.Player;
import com.runescape.game.msg.TestMessage;
import com.runescape.game.msg.codec.MessageEncoder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameBuilder;
import com.runescape.game.net.game.GameFrame.Type;
import com.runescape.game.util.Utils;

import io.netty.buffer.ByteBufAllocator;

public class TestMessageEncoder extends MessageEncoder<TestMessage> {

	public TestMessageEncoder() {
		super(TestMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, TestMessage message) throws IOException {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, 182, Type.VARIABLE_BYTE);
		
		Player target = null;
		try {
			target = World.getWorld().getPlayerByName("hello");
		} catch (Exception e) {
			/* Ignore */
		}
		int status = 0;
		
		if (target != null) {
			status = 1;
		}
		
		
		System.out.println("" + Utils.stringToLong(target.getUsername()));
		builder.put(DataType.LONG, Utils.stringToLong(target.getUsername())); //name
		builder.put(DataType.SHORT, status); //status
		builder.put(DataType.BYTE, 1);
		builder.putString("World 1");
		
		return builder.toGameFrame();
	}

}
