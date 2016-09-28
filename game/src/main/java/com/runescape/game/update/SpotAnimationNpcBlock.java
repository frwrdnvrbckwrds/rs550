package com.runescape.game.update;

import com.runescape.game.model.Npc;
import com.runescape.game.model.SpotAnimation;
import com.runescape.game.msg.NpcUpdateMessage;
import com.runescape.game.net.game.DataOrder;
import com.runescape.game.net.game.DataTransformation;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrameBuilder;

public final class SpotAnimationNpcBlock extends NpcBlock {

	private final SpotAnimation spotAnimation;

	public SpotAnimationNpcBlock(Npc npc) {
		super(0x80);
		this.spotAnimation = npc.getSpotAnimation();
	}

	@Override
	public void encode(NpcUpdateMessage message, GameFrameBuilder builder) {
		builder.put(DataType.SHORT, DataTransformation.ADD, spotAnimation.getId());
		builder.put(DataType.INT, DataOrder.LITTLE, (spotAnimation.getHeight() << 16) | spotAnimation.getDelay());
	}

}
