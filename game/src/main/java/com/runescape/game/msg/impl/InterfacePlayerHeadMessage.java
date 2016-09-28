package com.runescape.game.msg.impl;

import com.runescape.game.msg.Message;

public class InterfacePlayerHeadMessage extends Message {
	
	private final int id, componentId;
	
	public InterfacePlayerHeadMessage(int id, int componentId) {
		this.id = id;
		this.componentId = componentId;
	}
	
	public int getId() {
		return id;
	}
	
	public int getComponentId() {
		return componentId;
	}

}