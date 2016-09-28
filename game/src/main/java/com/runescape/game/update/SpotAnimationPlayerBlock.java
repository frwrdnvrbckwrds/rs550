package com.runescape.game.update;

import com.runescape.game.model.SpotAnimation;
import com.runescape.game.model.player.Player;
import com.runescape.game.msg.PlayerUpdateMessage;
import com.runescape.game.net.game.DataOrder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrameBuilder;

public final class SpotAnimationPlayerBlock extends PlayerBlock {

	private final SpotAnimation spotAnimation;

	public SpotAnimationPlayerBlock(Player player) {
		super(0x100);
		this.spotAnimation = player.getSpotAnimation();
	}

	@Override
	public void encode(PlayerUpdateMessage message, GameFrameBuilder builder) {
		builder.put(DataType.SHORT, DataOrder.LITTLE, spotAnimation.getId());
		builder.put(DataType.INT, (spotAnimation.getHeight() << 16) | spotAnimation.getDelay());
	}

}
