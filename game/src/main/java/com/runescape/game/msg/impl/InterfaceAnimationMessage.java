package com.runescape.game.msg.impl;

import com.runescape.game.msg.Message;

public class InterfaceAnimationMessage extends Message {
	
	private final int id, componentId, animationId;
	
	public InterfaceAnimationMessage(int id, int componentId, int animationId) {
		this.id = id;
		this.componentId = componentId;
		this.animationId = animationId;
	}
	
	public int getId() {
		return id;
	}
	
	public int getComponentId() {
		return componentId;
	}
	
	public int getAnimationId() {
		return animationId;
	}

}