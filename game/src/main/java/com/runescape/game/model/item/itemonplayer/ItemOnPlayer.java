package com.runescape.game.model.item.itemonplayer;

import com.runescape.game.model.player.Player;
import com.runescape.game.msg.ItemOnPlayerMessage;

public interface ItemOnPlayer {
	public void handle(Player player, ItemOnPlayerMessage message);
}
