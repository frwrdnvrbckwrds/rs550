package com.runescape.game.msg.codec.decoder;

import java.io.IOException;

import com.runescape.game.msg.RegionChangedMessage;
import com.runescape.game.msg.codec.MessageDecoder;
import com.runescape.game.net.game.GameFrame;

public final class RegionChangedMessageDecoder extends MessageDecoder<RegionChangedMessage> {

	private static final RegionChangedMessage REGION_CHANGED_MESSAGE = new RegionChangedMessage();

	public RegionChangedMessageDecoder() {
		super(113);
	}

	@Override
	public RegionChangedMessage decode(GameFrame frame) throws IOException {
		return REGION_CHANGED_MESSAGE;
	}

}
