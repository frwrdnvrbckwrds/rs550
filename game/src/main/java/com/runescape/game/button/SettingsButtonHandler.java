package com.runescape.game.button;

import com.runescape.game.model.Interface;
import com.runescape.game.model.player.Player;
import com.runescape.game.model.player.PlayerSettings;

public final class SettingsButtonHandler extends ButtonHandler {

	public SettingsButtonHandler() {
		super(Interface.SETTINGS);
	}

	@Override
	public void handle(Player player, int slot, int parameter) {
		PlayerSettings settings = player.getSettings();
		if (slot == 3) {
			settings.toggleRunning();
		} else if (slot == 4) {
			settings.toggleChatFancy();
		} else if (slot == 5) {
			settings.togglePrivateChatSplit();
		} else if (slot == 6) {
			settings.toggleTwoButtonMouse();
		} else if (slot == 7) {
			settings.toggleAcceptingAid();
		} else if (slot == 16) {
			player.getInterfaceSet().openWindow(Interface.DISPLAY_SETTINGS); // TODO needs 'please close the interface...' text?
		} else if (slot == 18) {
			player.getInterfaceSet().openWindow(Interface.AUDIO_SETTINGS);
		}
	}

}
