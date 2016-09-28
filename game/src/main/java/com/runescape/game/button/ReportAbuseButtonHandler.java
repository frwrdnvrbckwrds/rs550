package com.runescape.game.button;

import com.runescape.game.model.player.Player;

public class ReportAbuseButtonHandler extends ButtonHandler {

	public ReportAbuseButtonHandler() {
		super(751);
	}

	@Override
	public void handle(Player player, int slot, int parameter) {
		if (slot == 28) {
			player.getInterfaceSet().openWindow(553);
		}
	}

}
