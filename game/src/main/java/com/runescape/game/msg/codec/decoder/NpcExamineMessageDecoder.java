package com.runescape.game.msg.codec.decoder;

import java.io.IOException;

import com.runescape.game.msg.NpcExamineMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.DataOrder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameReader;

public final class NpcExamineMessageDecoder extends MessageDecoder<NpcExamineMessage> {
    
    public NpcExamineMessageDecoder() {
        super(Opcode.NPC_EXAMINE);
    }

    @Override
    public NpcExamineMessage decode(GameFrame frame) throws IOException {
        GameFrameReader reader = new GameFrameReader(frame);
        int id = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE);
        return new NpcExamineMessage(id);
    }
}
