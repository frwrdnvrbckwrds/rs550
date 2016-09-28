package com.runescape.game.model.combat;

import com.runescape.game.model.World;
import com.runescape.game.model.item.Equipment;
import com.runescape.game.model.player.Player;

public class Combat {
	private Player player;
	private boolean attacking;
	
	private AttackTask attackTask;
	
	public Combat(Player player) {
		this.player = player;
	}
	
	public void attack(Player target) {
		if (target.getCombat().inCombat() || inCombat()) {
			return;
		}
		
		if (attackTask == null) {
			attackTask = new AttackTask(player, target, (int) player.getEquipment().get(Equipment.WEAPON).getDefinition().getSpeed());
			World.getWorld().getTaskScheduler().schedule(attackTask);
		}
		
		attackTask.setDelay((int) player.getEquipment().get(Equipment.WEAPON).getDefinition().getSpeed());
		
		attacking = true;
	}
	
	public void stopAttacking() {
		attackTask = null;
		attacking = false;
	}
	
	public boolean inCombat() {
		return attacking;
	}
}
