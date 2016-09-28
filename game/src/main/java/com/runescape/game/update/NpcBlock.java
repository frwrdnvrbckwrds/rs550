package com.runescape.game.update;

import com.runescape.game.msg.NpcUpdateMessage;
import com.runescape.game.net.game.GameFrameBuilder;

public abstract class NpcBlock {

	private final int flag;

	public NpcBlock(int flag) {
		this.flag = flag;
	}

	public int getFlag() {
		return flag;
	}

	public abstract void encode(NpcUpdateMessage message, GameFrameBuilder builder);

}
