package com.runescape.game.update;

import net.scapeemulator.util.ChatUtils;

import java.util.Arrays;

import com.runescape.game.model.player.Player;
import com.runescape.game.msg.ChatMessage;
import com.runescape.game.msg.PlayerUpdateMessage;
import com.runescape.game.net.game.DataTransformation;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrameBuilder;

public final class ChatPlayerBlock extends PlayerBlock {

	private final ChatMessage chatMessage;
	private final int rights;

	public ChatPlayerBlock(Player player) {
		super(0x20);
		this.chatMessage = player.getChatMessage();
		this.rights = player.getRights();
	}

	@Override
	public void encode(PlayerUpdateMessage message, GameFrameBuilder builder) {
		byte[] bytes = new byte[256];
		int size = ChatUtils.pack(chatMessage.getText(), bytes);

		builder.put(DataType.SHORT, DataTransformation.ADD, (chatMessage.getColor() << 8) | chatMessage.getEffects());
		builder.put(DataType.BYTE, DataTransformation.ADD, rights);
		builder.put(DataType.BYTE, DataTransformation.SUBTRACT, size);
		builder.putBytesReverse(Arrays.copyOf(bytes, size));
	}

}
