package com.runescape.game.button;

import com.runescape.game.model.Interface;
import com.runescape.game.model.player.Player;

public final class EnergyOrbButtonHandler extends ButtonHandler {

	public EnergyOrbButtonHandler() {
		super(Interface.ENERGY_ORB);
	}

	@Override
	public void handle(Player player, int slot, int parameter) {
		if (slot == 1) {
			player.getSettings().toggleRunning();
		}
	}

}
