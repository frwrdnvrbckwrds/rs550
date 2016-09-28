package com.runescape.game.update;

import com.runescape.game.model.player.Player;
import com.runescape.game.msg.PlayerUpdateMessage;
import com.runescape.game.net.game.DataOrder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrameBuilder;

public class PlayerFaceToBlock extends PlayerBlock {
	private Player player;
	
	public PlayerFaceToBlock(Player player) {
		super(0x40);
		this.player = player;
	}

	@Override
	public void encode(PlayerUpdateMessage message, GameFrameBuilder builder) {
		builder.put(DataType.SHORT, DataOrder.LITTLE, 3230);
		builder.put(DataType.SHORT, 3235);
	}

}
