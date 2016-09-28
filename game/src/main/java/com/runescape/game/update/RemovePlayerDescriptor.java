package com.runescape.game.update;

import com.runescape.game.model.player.Player;
import com.runescape.game.msg.PlayerUpdateMessage;
import com.runescape.game.net.game.GameFrameBuilder;

public final class RemovePlayerDescriptor extends PlayerDescriptor {

	public RemovePlayerDescriptor(Player player, int[] tickets) {
		super(player, tickets);
	}

	@Override
	public void encodeDescriptor(PlayerUpdateMessage message, GameFrameBuilder builder, GameFrameBuilder blockBuilder) {
		builder.putBits(1, 1);
		builder.putBits(2, 3);
	}

}
