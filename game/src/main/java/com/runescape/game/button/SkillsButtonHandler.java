package com.runescape.game.button;

import com.runescape.game.model.Interface;
import com.runescape.game.model.player.Player;

public class SkillsButtonHandler extends ButtonHandler {
	private int[] interfaces = new int[28];
	
	public SkillsButtonHandler() {
		super(Interface.SKILLS);
	}

	@Override
	public void handle(Player player, int slot, int parameter) {
		int index = 0;
		for (int i = 126; i < 148; i++) {
			if (slot == i) {
				player.getInterfaceSet().openOverlay(interfaces[index]);
				break;
			}
			index++;
		}
	}

}
