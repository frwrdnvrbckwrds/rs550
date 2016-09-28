package com.runescape.game.update;

import com.runescape.game.model.Npc;
import com.runescape.game.model.mob.Direction;
import com.runescape.game.msg.NpcUpdateMessage;
import com.runescape.game.net.game.GameFrameBuilder;

public final class RunNpcDescriptor extends NpcDescriptor {

	private final Direction firstDirection, secondDirection;

	public RunNpcDescriptor(Npc npc) {
		super(npc);
		this.firstDirection = npc.getFirstDirection();
		this.secondDirection = npc.getSecondDirection();
	}

	@Override
	public void encodeDescriptor(NpcUpdateMessage message, GameFrameBuilder builder, GameFrameBuilder blockBuilder) {
		builder.putBits(1, 1);
		builder.putBits(2, 2);
		builder.putBits(1, 1);
		builder.putBits(3, firstDirection.toInteger());
		builder.putBits(3, secondDirection.toInteger());
		builder.putBits(1, isBlockUpdatedRequired() ? 1 : 0);
	}

}
