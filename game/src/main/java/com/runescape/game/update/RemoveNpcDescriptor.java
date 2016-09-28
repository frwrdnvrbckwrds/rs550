package com.runescape.game.update;

import com.runescape.game.model.Npc;
import com.runescape.game.msg.NpcUpdateMessage;
import com.runescape.game.net.game.GameFrameBuilder;

public final class RemoveNpcDescriptor extends NpcDescriptor {

	public RemoveNpcDescriptor(Npc npc) {
		super(npc);
	}

	@Override
	public void encodeDescriptor(NpcUpdateMessage message, GameFrameBuilder builder, GameFrameBuilder blockBuilder) {
		builder.putBits(1, 1);
		builder.putBits(2, 3);
	}

}
