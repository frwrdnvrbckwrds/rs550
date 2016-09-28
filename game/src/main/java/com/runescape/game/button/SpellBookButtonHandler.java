package com.runescape.game.button;

import com.runescape.game.model.Interface;
import com.runescape.game.model.player.Player;

public class SpellBookButtonHandler extends ButtonHandler {

	public SpellBookButtonHandler() {
		super(Interface.MAGIC);
	}

	@Override
	public void handle(Player player, int slot, int parameter) {
		System.out.println("Slot: " + slot + "\nParam: " + parameter);
	
		if (slot == 0) {
			//Home teleport
			
		}
	}

}
