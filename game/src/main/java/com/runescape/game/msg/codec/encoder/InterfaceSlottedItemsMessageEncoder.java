package com.runescape.game.msg.codec.encoder;

import com.runescape.game.model.SlottedItem;
import com.runescape.game.model.item.Item;
import com.runescape.game.msg.InterfaceSlottedItemsMessage;
import com.runescape.game.msg.codec.MessageEncoder;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrame;
import com.runescape.game.net.game.GameFrameBuilder;
import com.runescape.game.net.game.GameFrame.Type;

import io.netty.buffer.ByteBufAllocator;

public final class InterfaceSlottedItemsMessageEncoder extends MessageEncoder<InterfaceSlottedItemsMessage> {

	public InterfaceSlottedItemsMessageEncoder() {
		super(InterfaceSlottedItemsMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, InterfaceSlottedItemsMessage message) {
		SlottedItem[] items = message.getItems();

		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.INTERFACE_SLOTTED_ITEM, Type.VARIABLE_SHORT);
		builder.put(DataType.INT, (message.getId() << 16) | message.getSlot());
		builder.put(DataType.SHORT, message.getType());

		for (SlottedItem slottedItem : items) {
			int slot = slottedItem.getSlot();
			builder.putSmart(slot);

			Item item = slottedItem.getItem();
			if (item == null) {
				builder.put(DataType.SHORT, 0);
			} else {
				int amount = item.getAmount();
				builder.put(DataType.SHORT, item.getId() + 1);
				if (amount >= 255) {
					builder.put(DataType.BYTE, 255);
					builder.put(DataType.INT, amount);
				} else {
					builder.put(DataType.BYTE, amount);
				}
			}
		}

		return builder.toGameFrame();
	}

}