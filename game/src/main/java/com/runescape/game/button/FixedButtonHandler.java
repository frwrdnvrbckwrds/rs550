package com.runescape.game.button;

import com.runescape.game.model.Interface;
import com.runescape.game.model.player.Player;

public final class FixedButtonHandler extends ButtonHandler {

	public FixedButtonHandler() {
		super(Interface.FIXED);
	}

	@Override
	public void handle(Player player, int slot, int parameter) {
		if (slot == 70) {
			player.getInterfaceSet().openWorldMap();
		}
	}

}
