package com.runescape.game.update;

import com.runescape.game.model.Npc;
import com.runescape.game.model.mob.Direction;
import com.runescape.game.msg.NpcUpdateMessage;
import com.runescape.game.net.game.GameFrameBuilder;

public final class WalkNpcDescriptor extends NpcDescriptor {

	private final Direction direction;

	public WalkNpcDescriptor(Npc npc) {
		super(npc);
		this.direction = npc.getFirstDirection();
	}

	@Override
	public void encodeDescriptor(NpcUpdateMessage message, GameFrameBuilder builder, GameFrameBuilder blockBuilder) {
		builder.putBits(1, 1);
		builder.putBits(2, 1);
		builder.putBits(3, direction.toInteger());
		builder.putBits(1, isBlockUpdatedRequired() ? 1 : 0);
	}

}
