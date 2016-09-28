package com.runescape.game.update;

import com.runescape.game.model.Npc;
import com.runescape.game.model.Position;
import com.runescape.game.model.mob.Direction;
import com.runescape.game.msg.NpcUpdateMessage;
import com.runescape.game.net.game.GameFrameBuilder;

public final class AddNpcDescriptor extends NpcDescriptor {

	private final int id, type;
	private final Direction direction;
	private final Position position;

	public AddNpcDescriptor(Npc npc) {
		super(npc);
		this.id = npc.getId();
		this.type = npc.getType();
		this.direction = npc.getMostRecentDirection();
		this.position = npc.getPosition();
	}

	@Override
	public void encodeDescriptor(NpcUpdateMessage message, GameFrameBuilder builder, GameFrameBuilder blockBuilder) {
		int x = position.getX() - message.getPosition().getX();
		int y = position.getY() - message.getPosition().getY();

		builder.putBits(15, id);
        builder.putBits(5, y);
        builder.putBits(3, direction.toInteger());
        builder.putBits(5, x);
        builder.putBits(14, type);
        builder.putBits(1, isBlockUpdatedRequired() ? 1 : 0);
		builder.putBits(1, 1); // check
	}

}
