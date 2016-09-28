package com.runescape.game.msg.codec.decoder;

import java.io.IOException;

import com.runescape.game.msg.MusicPlayingMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameReader;

public class MusicPlayingMessageDecoder extends MessageDecoder<MusicPlayingMessage> {

	public MusicPlayingMessageDecoder() {
		super(Opcode.MUSIC);
	}

	@Override
	public MusicPlayingMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int id = (int) reader.getSigned(DataType.INT);
		return new MusicPlayingMessage(id);
	}

}
