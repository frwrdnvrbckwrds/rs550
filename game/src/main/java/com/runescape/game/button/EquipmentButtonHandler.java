package com.runescape.game.button;

import com.runescape.game.model.Interface;
import com.runescape.game.model.player.Player;

public class EquipmentButtonHandler extends ButtonHandler {
	
	public EquipmentButtonHandler() {
		super(Interface.EQUIPMENT);
	}

	@Override
	public void handle(Player player, int slot, int parameter) {
		if (slot == 56) {
			player.getInterfaceSet().openEquipmentTab();
		} else if (slot == 63) {
			//Price Check
		} else if (slot == 53) {
			//Items on Death
		}
	}

}
