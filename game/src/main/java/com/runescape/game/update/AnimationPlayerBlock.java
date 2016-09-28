package com.runescape.game.update;

import com.runescape.game.model.mob.Animation;
import com.runescape.game.model.player.Player;
import com.runescape.game.msg.PlayerUpdateMessage;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrameBuilder;

public final class AnimationPlayerBlock extends PlayerBlock {

	private final Animation animation;

	public AnimationPlayerBlock(Player player) {
		super(0x2);
		this.animation = player.getAnimation();
	}

	@Override
	public void encode(PlayerUpdateMessage message, GameFrameBuilder builder) {
		builder.put(DataType.SHORT, animation.getId());
		builder.put(DataType.BYTE, animation.getDelay());
	}

}
