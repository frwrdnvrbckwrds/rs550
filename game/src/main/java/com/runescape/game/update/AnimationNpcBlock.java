package com.runescape.game.update;

import com.runescape.game.model.Npc;
import com.runescape.game.model.mob.Animation;
import com.runescape.game.msg.NpcUpdateMessage;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrameBuilder;

public final class AnimationNpcBlock extends NpcBlock {

	private final Animation animation;

	public AnimationNpcBlock(Npc npc) {
		super(0x10);
		this.animation = npc.getAnimation();
	}

	@Override
	public void encode(NpcUpdateMessage message, GameFrameBuilder builder) {
		builder.put(DataType.SHORT, animation.getId());
		builder.put(DataType.BYTE, animation.getDelay());
	}

}
