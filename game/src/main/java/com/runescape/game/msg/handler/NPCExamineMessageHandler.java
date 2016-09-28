package com.runescape.game.msg.handler;

import com.runescape.game.model.definition.NPCDefinitions;
import com.runescape.game.model.player.Player;
import com.runescape.game.msg.NpcExamineMessage;

public final class NPCExamineMessageHandler extends MessageHandler<NpcExamineMessage> {

	public void handle(Player player, NpcExamineMessage msg) {
		String examine = NPCDefinitions.forId(msg.getType()).getExamine();
		if (examine.equalsIgnoreCase("unknown") || examine.length() <= 0) {
			examine = "It's a " + NPCDefinitions.forId(msg.getType()).getName();
		}
		player.sendMessage(examine);
	}
}
