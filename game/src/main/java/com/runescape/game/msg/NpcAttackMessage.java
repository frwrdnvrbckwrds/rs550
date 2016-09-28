package com.runescape.game.msg;

import com.runescape.game.model.Npc;

public final class NpcAttackMessage extends Message {
	private Npc npc;
	
	public NpcAttackMessage(Npc npc) {
		this.npc = npc;
	}
	
	public Npc getNpc() {
		return npc;
	}
}
