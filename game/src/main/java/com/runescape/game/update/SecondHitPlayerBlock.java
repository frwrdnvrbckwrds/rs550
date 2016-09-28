package com.runescape.game.update;

import com.runescape.game.model.combat.Hit;
import com.runescape.game.model.player.Player;
import com.runescape.game.msg.PlayerUpdateMessage;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrameBuilder;

public class SecondHitPlayerBlock extends PlayerBlock {
	private Player player;
	
	public SecondHitPlayerBlock(Player player) {
		super(0x400);
		this.player = player;
	}
	
	

	@Override
	public void encode(PlayerUpdateMessage message, GameFrameBuilder builder) {
		Hit hit = player.getNextHit();
		builder.put(DataType.BYTE, hit.getDamage()); //damage - this may be wrong
		builder.put(DataType.BYTE, hit.getType().getType()); //type
	}
}