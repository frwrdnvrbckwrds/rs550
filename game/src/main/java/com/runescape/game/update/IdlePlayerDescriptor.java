package com.runescape.game.update;

import com.runescape.game.model.player.Player;
import com.runescape.game.msg.PlayerUpdateMessage;
import com.runescape.game.net.game.GameFrameBuilder;

public final class IdlePlayerDescriptor extends PlayerDescriptor {

	public IdlePlayerDescriptor(Player player, int[] tickets) {
		super(player, tickets);
	}

	@Override
	public void encodeDescriptor(PlayerUpdateMessage message, GameFrameBuilder builder, GameFrameBuilder blockBuilder) {
		if (isBlockUpdatedRequired()) {
			builder.putBits(1, 1);
			builder.putBits(2, 0);
		} else {
			builder.putBits(1, 0);
		}
	}

}
