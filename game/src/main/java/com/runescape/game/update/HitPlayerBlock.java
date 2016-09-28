package com.runescape.game.update;

import com.runescape.game.model.Skill;
import com.runescape.game.model.combat.Hit;
import com.runescape.game.model.player.Player;
import com.runescape.game.msg.PlayerUpdateMessage;
import com.runescape.game.net.game.DataTransformation;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrameBuilder;

public class HitPlayerBlock extends PlayerBlock {
	private Player player;
	
	public HitPlayerBlock(Player player) {
		super(0x1);
		this.player = player;
	}

	@Override
	public void encode(PlayerUpdateMessage message, GameFrameBuilder builder) {
		Hit hit = player.getNextHit();
		
		builder.put(DataType.BYTE, hit.getDamage()); //damage - this may be wrong
		builder.put(DataType.BYTE, hit.getType().getType()); //type
		
		int ratio = player.getSkillSet().getCurrentLevel(Skill.HITPOINTS) * 255 / player.getSkillSet().getLevel(Skill.HITPOINTS);
		builder.put(DataType.BYTE, DataTransformation.NEGATE, ratio); //ratio
	}

}
