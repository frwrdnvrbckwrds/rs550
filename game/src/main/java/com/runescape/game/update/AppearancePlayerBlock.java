package com.runescape.game.update;

import com.runescape.game.model.item.Equipment;
import com.runescape.game.model.item.EquipmentDefinition;
import com.runescape.game.model.item.Inventory;
import com.runescape.game.model.item.Item;
import com.runescape.game.model.player.Player;
import com.runescape.game.model.player.appearance.Appearance;
import com.runescape.game.model.player.appearance.Appearance.Feature;
import com.runescape.game.model.player.appearance.Gender;
import com.runescape.game.msg.PlayerUpdateMessage;
import com.runescape.game.net.game.DataTransformation;
import com.runescape.game.net.game.DataType;
import com.runescape.game.net.game.GameFrameBuilder;

import net.scapeemulator.util.Base37Utils;

public final class AppearancePlayerBlock extends PlayerBlock {

	private final String username;
	private final Appearance appearance;
	private final Inventory equipment;
	private final int stance, combat, skill;

	public AppearancePlayerBlock(Player player) {
		super(0x8);
		this.username = player.getUsername();
		this.appearance = player.getAppearance();
		this.equipment = new Inventory(player.getEquipment());
		this.stance = player.getStance();
		this.combat = player.getSkillSet().getCombatLevel();
		this.skill = player.getSkillSet().getTotalLevel();
	}

	@Override
	public void encode(PlayerUpdateMessage message, GameFrameBuilder builder) {
		Gender gender = appearance.getGender();

		GameFrameBuilder propertiesBuilder = new GameFrameBuilder(builder.getAllocator());

		/*
		 * flags field:
		 *   bit 0   - gender (0 = male, 1 = female)
		 *   bit 1   - unused
		 *   bit 2   - show skill level instead of combat level
		 *   bit 3-5 - unknown
		 *   bit 6-7 - unknown
		 */
		int flags = gender.ordinal();
		propertiesBuilder.put(DataType.BYTE, flags); 
		propertiesBuilder.put(DataType.BYTE, -1); // pk icon
		propertiesBuilder.put(DataType.BYTE, -1); // prayer icon
		
		Item item = equipment.get(Equipment.HEAD);
		if (item != null) {
			propertiesBuilder.put(DataType.SHORT, 0x8000 | EquipmentDefinition.forId(item.getId()).getEquipmentId());
		} else {
			propertiesBuilder.put(DataType.BYTE, 0);
		}

		item = equipment.get(Equipment.CAPE);
		if (item != null) {
			propertiesBuilder.put(DataType.SHORT, 0x8000 | EquipmentDefinition.forId(item.getId()).getEquipmentId());
		} else {
			propertiesBuilder.put(DataType.BYTE, 0);
		}

		item = equipment.get(Equipment.NECK);
		if (item != null) {
			propertiesBuilder.put(DataType.SHORT, 0x8000 | EquipmentDefinition.forId(item.getId()).getEquipmentId());
		} else {
			propertiesBuilder.put(DataType.BYTE, 0);
		}

		item = equipment.get(Equipment.WEAPON);
		if (item != null) {
			propertiesBuilder.put(DataType.SHORT, 0x8000 | EquipmentDefinition.forId(item.getId()).getEquipmentId());
		} else {
			propertiesBuilder.put(DataType.BYTE, 0);
		}

		item = equipment.get(Equipment.BODY);
		if (item != null) {
			propertiesBuilder.put(DataType.SHORT, 0x8000 | EquipmentDefinition.forId(item.getId()).getEquipmentId());
		} else {
			propertiesBuilder.put(DataType.SHORT, 0x100 | appearance.getStyle(Feature.TORSO));
		}

		item = equipment.get(Equipment.SHIELD);
		if (item != null) {
			propertiesBuilder.put(DataType.SHORT, 0x8000 | EquipmentDefinition.forId(item.getId()).getEquipmentId());
		} else {
			propertiesBuilder.put(DataType.BYTE, 0);
		}

		boolean fullBody = false;
		item = equipment.get(Equipment.BODY);
		if (item != null)
			fullBody = EquipmentDefinition.forId(item.getId()).isFullBody();

		if (!fullBody) {
			propertiesBuilder.put(DataType.SHORT, 0x100 | appearance.getStyle(Feature.ARMS));
		} else {
			propertiesBuilder.put(DataType.BYTE, 0);
		}

		item = equipment.get(Equipment.LEGS);
		if (item != null) {
			propertiesBuilder.put(DataType.SHORT, 0x8000 | EquipmentDefinition.forId(item.getId()).getEquipmentId());
		} else {
			propertiesBuilder.put(DataType.SHORT, 0x100 | appearance.getStyle(Feature.LEGS));
		}

		boolean fullHelm = false, fullMask = false;
		item = equipment.get(Equipment.HEAD);
		if (item != null) {
			fullHelm = EquipmentDefinition.forId(item.getId()).isFullHelm();
			fullMask = EquipmentDefinition.forId(item.getId()).isFullMask();
		}
		if (!fullHelm && !fullMask) {
			propertiesBuilder.put(DataType.SHORT, 0x100 | appearance.getStyle(Feature.HAIR));
		} else {
			propertiesBuilder.put(DataType.BYTE, 0);
		}

		item = equipment.get(Equipment.HANDS);
		if (item != null) {
			propertiesBuilder.put(DataType.SHORT, 0x8000 | EquipmentDefinition.forId(item.getId()).getEquipmentId());
		} else {
			propertiesBuilder.put(DataType.SHORT, 0x100 | appearance.getStyle(Feature.WRISTS));
		}

		item = equipment.get(Equipment.FEET);
		if (item != null) {
			propertiesBuilder.put(DataType.SHORT, 0x8000 | EquipmentDefinition.forId(item.getId()).getEquipmentId());
		} else {
			propertiesBuilder.put(DataType.SHORT, 0x100 | appearance.getStyle(Feature.FEET));
		}

		item = equipment.get(Equipment.HEAD); // TODO check
		if (gender == Gender.MALE && !fullMask && !fullHelm) {
			propertiesBuilder.put(DataType.SHORT, 0x100 | appearance.getStyle(Feature.FACIAL_HAIR));
		} else {
			propertiesBuilder.put(DataType.BYTE, 0);
		}

		propertiesBuilder.put(DataType.BYTE, appearance.getColor(Feature.HAIR));
        propertiesBuilder.put(DataType.BYTE, appearance.getColor(Feature.TORSO));
        propertiesBuilder.put(DataType.BYTE, appearance.getColor(Feature.LEGS));
        propertiesBuilder.put(DataType.BYTE, appearance.getColor(Feature.FEET));
        propertiesBuilder.put(DataType.BYTE, appearance.getColor(Feature.SKIN));

		propertiesBuilder.put(DataType.SHORT, stance);
		propertiesBuilder.put(DataType.LONG, Base37Utils.encodeBase37(username));
		propertiesBuilder.put(DataType.BYTE, combat);
		if ((flags & 0x4) != 0) {
			propertiesBuilder.put(DataType.SHORT, skill);
		} else {
			propertiesBuilder.put(DataType.BYTE, 0);
			propertiesBuilder.put(DataType.BYTE, 0);
		}
		propertiesBuilder.put(DataType.BYTE, 0);
		/* if the above byte is non-zero, four unknown shorts are written */

		builder.put(DataType.BYTE, DataTransformation.NEGATE, propertiesBuilder.getLength());
		builder.putRawBuilder(propertiesBuilder, DataTransformation.ADD);
	}

}
