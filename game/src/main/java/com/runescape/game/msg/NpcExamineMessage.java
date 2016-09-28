package com.runescape.game.msg;

public class NpcExamineMessage extends Message {
	private int type;
	
	public NpcExamineMessage(int id) {
		this.type = id;
	}
	
	public int getType() {
		return type;
	}
}
