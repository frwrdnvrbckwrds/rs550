package com.runescape.game.msg.handler;

import com.runescape.game.model.definition.ObjectDefinitions;
import com.runescape.game.model.player.Player;
import com.runescape.game.msg.ObjectExamineMessage;

import net.scapeemulator.cache.def.ObjectDefinition;

public class ObjectExamineMessageHandler extends MessageHandler<ObjectExamineMessage> {

	@Override
	public void handle(Player player, ObjectExamineMessage message) {
		String examine = "";
		ObjectDefinition def = ObjectDefinitions.forId(message.getId());
		if (def != null) {
			examine = def.getDescription();
		} else {
			examine = "It's an object.";
		}
		player.sendMessage(examine);
	}

}
