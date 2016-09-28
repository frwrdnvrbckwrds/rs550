package com.runescape.game.msg.handler;

import com.runescape.game.model.Interface;
import com.runescape.game.model.InterfaceSet;
import com.runescape.game.model.InterfaceSet.DisplayMode;
import com.runescape.game.model.player.Player;
import com.runescape.game.msg.DisplayMessage;

public final class DisplayMessageHandler extends MessageHandler<DisplayMessage> {

	@Override
	public void handle(Player player, DisplayMessage message) {
		InterfaceSet interfaces = player.getInterfaceSet();
		DisplayMode currentMode = interfaces.getDisplayMode();
		DisplayMode newMode;
		if (message.getMode() == 0 || message.getMode() == 1)
			newMode = DisplayMode.FIXED;
		else
			newMode = DisplayMode.RESIZABLE;

		if (newMode != currentMode) {
			interfaces.setDisplayMode(newMode);
			interfaces.init();
			interfaces.openWindow(Interface.DISPLAY_SETTINGS); // TODO close on the old root?
		}
	}

}
