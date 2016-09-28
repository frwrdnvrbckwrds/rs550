package com.runescape.game.msg;

import com.runescape.game.model.Position;

public final class RegionChangeMessage extends Message {

	private final Position position;

	public RegionChangeMessage(Position position) {
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}

}
