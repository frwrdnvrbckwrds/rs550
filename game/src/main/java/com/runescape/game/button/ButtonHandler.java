package com.runescape.game.button;

import com.runescape.game.model.player.Player;

public abstract class ButtonHandler {

	private final int id;

	public ButtonHandler(int id) {
		this.id = id;
	}

	public final int getId() {
		return id;
	}

	public abstract void handle(Player player, int slot, int parameter);

}
