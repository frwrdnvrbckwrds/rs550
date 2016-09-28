package com.runescape.game.msg.codec.decoder;

import java.io.IOException;

import com.runescape.game.msg.IdleLogoutMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.GameFrame;

public final class IdleLogoutMessageDecoder extends MessageDecoder<IdleLogoutMessage> {

	private static final IdleLogoutMessage IDLE_LOGOUT_MESSAGE = new IdleLogoutMessage();

	public IdleLogoutMessageDecoder() {
		super(Opcode.IDLE_LOGOUT);
	}

	@Override
	public IdleLogoutMessage decode(GameFrame frame) throws IOException {
		return IDLE_LOGOUT_MESSAGE;
	}

}
