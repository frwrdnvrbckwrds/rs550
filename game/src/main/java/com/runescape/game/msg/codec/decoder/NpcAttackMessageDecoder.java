package com.runescape.game.msg.codec.decoder;

import java.io.IOException;

import com.runescape.game.model.Npc;
import com.runescape.game.model.World;
import com.runescape.game.msg.NpcAttackMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.DataOrder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameReader;

public class NpcAttackMessageDecoder extends MessageDecoder<NpcAttackMessage> {

	public NpcAttackMessageDecoder() {
		super(Opcode.NPC_ATTACK_OPTION);
	}

	@Override
	public NpcAttackMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int npcId = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE);
		int unknown = (int) reader.getUnsigned(DataType.BYTE);
        final Npc npc = World.getWorld().getNpcs().get(npcId);
		return new NpcAttackMessage(npc);
	}

}
