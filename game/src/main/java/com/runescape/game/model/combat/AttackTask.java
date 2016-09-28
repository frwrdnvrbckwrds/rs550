package com.runescape.game.model.combat;

import com.runescape.game.model.mob.Mob;
import com.runescape.game.model.player.Player;
import com.runescape.game.task.ScheduledTask;

public class AttackTask extends ScheduledTask {
	private Player mob;
	private Mob target;
	
	public AttackTask(Player mob, Mob target, int delay) {
		super(delay, true);
		this.mob = mob;
		this.target = target;
	}

	@Override
	public void execute() {
		if (mob.getCombat().inCombat()) {
			int hit = (int) Math.ceil(Math.random() * 100);
			target.appendHit(new Hit(hit, HitType.NORMAL_DAMAGE));
		}
	}

}
