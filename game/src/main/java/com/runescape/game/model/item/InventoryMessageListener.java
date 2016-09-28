package com.runescape.game.model.item;

import com.runescape.game.model.SlottedItem;
import com.runescape.game.model.player.Player;
import com.runescape.game.msg.InterfaceItemsMessage;
import com.runescape.game.msg.InterfaceResetItemsMessage;
import com.runescape.game.msg.InterfaceSlottedItemsMessage;

public final class InventoryMessageListener implements InventoryListener {

	private final Player player;
	private final int id, slot, type;

	public InventoryMessageListener(Player player, int id, int slot, int type) {
		this.player = player;
		this.id = id;
		this.slot = slot;
		this.type = type;
	}

	@Override
	public void itemChanged(Inventory inventory, int slot, Item item) {
		SlottedItem[] items = new SlottedItem[] {
			new SlottedItem(slot, item)
		};
		player.send(new InterfaceSlottedItemsMessage(id, this.slot, type, items));
	}

	@Override
	public void itemsChanged(Inventory inventory) {
		if (inventory.isEmpty()) {
			// TODO: consider how this interacts with the 'type'?
			player.send(new InterfaceResetItemsMessage(id, slot));
		} else {
			Item[] items = inventory.toArray();
			player.send(new InterfaceItemsMessage(id, slot, type, items));
		}
	}

	@Override
	public void capacityExceeded(Inventory inventory) {
		/* empty */
	}

}
