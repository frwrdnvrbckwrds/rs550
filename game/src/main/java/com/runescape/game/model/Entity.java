package com.runescape.game.model;

public abstract class Entity {

	protected Position position = new Position(3222, 3222);

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}