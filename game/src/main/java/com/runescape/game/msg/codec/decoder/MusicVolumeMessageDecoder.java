package com.runescape.game.msg.codec.decoder;

import java.io.IOException;

import com.runescape.game.msg.MusicVolumeMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameReader;

public class MusicVolumeMessageDecoder extends MessageDecoder<MusicVolumeMessage> {

	public MusicVolumeMessageDecoder() {
		super(Opcode.MUSIC_VOLUME_CHANGE);
	}

	@Override
	public MusicVolumeMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int volume = (int) reader.getSigned(DataType.INT);
		return new MusicVolumeMessage(volume);
	}

}
