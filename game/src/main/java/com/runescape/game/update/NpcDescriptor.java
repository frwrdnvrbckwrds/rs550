package com.runescape.game.update;

import java.util.HashMap;
import java.util.Map;

import com.runescape.game.model.Npc;
import com.runescape.game.model.mob.Direction;
import com.runescape.game.msg.NpcUpdateMessage;
import com.runescape.game.net.game.DataOrder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrameBuilder;

public abstract class NpcDescriptor {

	public static NpcDescriptor create(Npc npc) {
		Direction firstDirection = npc.getFirstDirection();
		Direction secondDirection = npc.getSecondDirection();

		if (firstDirection == Direction.NONE)
			return new IdleNpcDescriptor(npc);
		else if (secondDirection == Direction.NONE)
			return new WalkNpcDescriptor(npc);
		else
			return new RunNpcDescriptor(npc);
	}

	private final Map<Class<? extends NpcBlock>, NpcBlock> blocks = new HashMap<>();

	public NpcDescriptor(Npc npc) {
		if (npc.isAnimationUpdated())
			addBlock(new AnimationNpcBlock(npc));

		if (npc.isSpotAnimationUpdated())
			addBlock(new SpotAnimationNpcBlock(npc));
	}

	private void addBlock(NpcBlock block) {
		blocks.put(block.getClass(), block);
	}

	public boolean isBlockUpdatedRequired() {
		return !blocks.isEmpty();
	}

	public void encode(NpcUpdateMessage message, GameFrameBuilder builder, GameFrameBuilder blockBuilder) {
		encodeDescriptor(message, builder, blockBuilder);

		if (isBlockUpdatedRequired()) {
			int flags = 0;
			for (NpcBlock block : blocks.values())
				flags |= block.getFlag();

			if (flags > 0xFF) {
				flags |= 0x1;
				blockBuilder.put(DataType.SHORT, DataOrder.LITTLE, flags);
			} else {
				blockBuilder.put(DataType.BYTE, flags);
			}

			encodeBlock(message, blockBuilder, AnimationNpcBlock.class);
			encodeBlock(message, blockBuilder, SpotAnimationNpcBlock.class);
		}
	}

	private void encodeBlock(NpcUpdateMessage message, GameFrameBuilder builder, Class<? extends NpcBlock> type) {
		NpcBlock block = blocks.get(type);
		if (block != null)
			block.encode(message, builder);
	}

	public abstract void encodeDescriptor(NpcUpdateMessage message, GameFrameBuilder builder, GameFrameBuilder blockBuilder);

}
