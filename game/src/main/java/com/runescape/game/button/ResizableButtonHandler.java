package com.runescape.game.button;

import com.runescape.game.model.Interface;
import com.runescape.game.model.player.Player;

public final class ResizableButtonHandler extends ButtonHandler {

	public ResizableButtonHandler() {
		super(Interface.RESIZABLE);
	}

	@Override
	public void handle(Player player, int slot, int parameter) {
		if (slot == 110) {
			player.getInterfaceSet().openWorldMap();
		}
	}

}
