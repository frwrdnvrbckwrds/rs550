package com.runescape.game.msg.codec.decoder;

import java.io.IOException;

import com.runescape.game.msg.FriendMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameReader;

public class RemoveFriendMessageDecoder extends MessageDecoder<FriendMessage> {

	public RemoveFriendMessageDecoder() {
		super(Opcode.DELETE_FRIEND);
	}

	@Override
	public FriendMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		long nameLong = reader.getSigned(DataType.LONG);
		return new FriendMessage(nameLong);
	}

}
