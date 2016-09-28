package com.runescape.game.msg.codec.decoder;

import java.io.IOException;

import com.runescape.game.msg.ObjectExamineMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameReader;

public class ObjectExamineMessageDecoder extends MessageDecoder<ObjectExamineMessage> {
    
    public ObjectExamineMessageDecoder() {
        super(Opcode.EXAMINE_OBJECT);
    }

    @Override
    public ObjectExamineMessage decode(GameFrame frame) throws IOException {
        GameFrameReader reader = new GameFrameReader(frame);
        int id = (int) reader.getUnsigned(DataType.SHORT);
        return new ObjectExamineMessage(id);
    }
}
