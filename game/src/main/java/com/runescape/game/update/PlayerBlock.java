package com.runescape.game.update;

import com.runescape.game.msg.PlayerUpdateMessage;
import com.runescape.game.net.game.GameFrameBuilder;

public abstract class PlayerBlock {

	private final int flag;

	public PlayerBlock(int flag) {
		this.flag = flag;
	}

	public int getFlag() {
		return flag;
	}

	public abstract void encode(PlayerUpdateMessage message, GameFrameBuilder builder);

}
