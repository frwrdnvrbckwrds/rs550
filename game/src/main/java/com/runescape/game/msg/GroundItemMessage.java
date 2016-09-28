package com.runescape.game.msg;

import com.runescape.game.model.Position;
import com.runescape.game.model.item.Item;

public class GroundItemMessage extends Message {
	private Item item;
	private Position positon;
	
	public GroundItemMessage(Item item, Position position) {
		this.item = item;
		this.positon = position;
	}
	
	public Item getItem() {
		return item;
	}
	
	public Position getPositon() {
		return positon;
	}
}
