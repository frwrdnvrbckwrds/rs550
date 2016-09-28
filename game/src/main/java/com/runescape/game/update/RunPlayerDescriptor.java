package com.runescape.game.update;

import com.runescape.game.model.mob.Direction;
import com.runescape.game.model.player.Player;
import com.runescape.game.msg.PlayerUpdateMessage;
import com.runescape.game.net.game.GameFrameBuilder;

public final class RunPlayerDescriptor extends PlayerDescriptor {

	private final Direction firstDirection, secondDirection;

	public RunPlayerDescriptor(Player player, int[] tickets) {
		super(player, tickets);
		this.firstDirection = player.getFirstDirection();
		this.secondDirection = player.getSecondDirection();
	}

	@Override
	public void encodeDescriptor(PlayerUpdateMessage message, GameFrameBuilder builder, GameFrameBuilder blockBuilder) {
		builder.putBits(1, 1);
		builder.putBits(2, 2);
		builder.putBits(1, 1); // TODO what is this?
		builder.putBits(3, firstDirection.toInteger());
		builder.putBits(3, secondDirection.toInteger());
		builder.putBits(1, isBlockUpdatedRequired() ? 1 : 0);
	}

}
