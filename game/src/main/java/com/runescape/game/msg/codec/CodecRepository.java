package com.runescape.game.msg.codec;

import java.util.HashMap;
import java.util.Map;

import com.runescape.game.msg.Message;
import com.runescape.game.msg.codec.decoder.AddFriendMessageDecoder;
import com.runescape.game.msg.codec.decoder.ButtonMessageDecoder;
import com.runescape.game.msg.codec.decoder.CameraMessageDecoder;
import com.runescape.game.msg.codec.decoder.ChatMessageDecoder;
import com.runescape.game.msg.codec.decoder.ClickChatBoxMessageDecoder;
import com.runescape.game.msg.codec.decoder.ClickItemMessageDecoder;
import com.runescape.game.msg.codec.decoder.ClickMessageDecoder;
import com.runescape.game.msg.codec.decoder.CommandMessageDecoder;
import com.runescape.game.msg.codec.decoder.DisplayMessageDecoder;
import com.runescape.game.msg.codec.decoder.DropItemMessageDecoder;
import com.runescape.game.msg.codec.decoder.EquipItemMessageDecoder;
import com.runescape.game.msg.codec.decoder.ExtendedButtonMessageDecoder;
import com.runescape.game.msg.codec.decoder.FlagsMessageDecoder;
import com.runescape.game.msg.codec.decoder.FocusMessageDecoder;
import com.runescape.game.msg.codec.decoder.IdleLogoutMessageDecoder;
import com.runescape.game.msg.codec.decoder.InterfaceClosedMessageDecoder;
import com.runescape.game.msg.codec.decoder.ItemExamineMessageDecoder;
import com.runescape.game.msg.codec.decoder.ItemOnItemMessageDecoder;
import com.runescape.game.msg.codec.decoder.ItemOnObjectMessageDecoder;
import com.runescape.game.msg.codec.decoder.ItemOnPlayerMessageDecoder;
import com.runescape.game.msg.codec.decoder.MagicOnItemMessageDecoder;
import com.runescape.game.msg.codec.decoder.MoveItemMessageDecoder;
import com.runescape.game.msg.codec.decoder.MusicPlayingMessageDecoder;
import com.runescape.game.msg.codec.decoder.MusicVolumeMessageDecoder;
import com.runescape.game.msg.codec.decoder.NpcAttackMessageDecoder;
import com.runescape.game.msg.codec.decoder.NpcExamineMessageDecoder;
import com.runescape.game.msg.codec.decoder.ObjectExamineMessageDecoder;
import com.runescape.game.msg.codec.decoder.ObjectOptionOneMessageDecoder;
import com.runescape.game.msg.codec.decoder.ObjectOptionTwoMessageDecoder;
import com.runescape.game.msg.codec.decoder.Opcode;
import com.runescape.game.msg.codec.decoder.OperateItemMessageDecoder;
import com.runescape.game.msg.codec.decoder.PingMessageDecoder;
import com.runescape.game.msg.codec.decoder.PrivateMessageDecoder;
import com.runescape.game.msg.codec.decoder.QuickChatMessageDecoder;
import com.runescape.game.msg.codec.decoder.RegionChangedMessageDecoder;
import com.runescape.game.msg.codec.decoder.RemoveFriendMessageDecoder;
import com.runescape.game.msg.codec.decoder.RemoveItemMessageDecoder;
import com.runescape.game.msg.codec.decoder.SequenceNumberMessageDecoder;
import com.runescape.game.msg.codec.decoder.SwapItemsMessageDecoder;
import com.runescape.game.msg.codec.decoder.WalkMessageDecoder;
import com.runescape.game.msg.codec.encoder.ChatSettingsMessageEncoder;
import com.runescape.game.msg.codec.encoder.ConfigMessageEncoder;
import com.runescape.game.msg.codec.encoder.EnergyMessageEncoder;
import com.runescape.game.msg.codec.encoder.FriendsStatusMessageEncoder;
import com.runescape.game.msg.codec.encoder.GroundItemMessageEncoder;
import com.runescape.game.msg.codec.encoder.InterfaceCloseMessageEncoder;
import com.runescape.game.msg.codec.encoder.InterfaceItemsMessageEncoder;
import com.runescape.game.msg.codec.encoder.InterfaceOpenMessageEncoder;
import com.runescape.game.msg.codec.encoder.InterfaceResetItemsMessageEncoder;
import com.runescape.game.msg.codec.encoder.InterfaceRootMessageEncoder;
import com.runescape.game.msg.codec.encoder.InterfaceSlottedItemsMessageEncoder;
import com.runescape.game.msg.codec.encoder.InterfaceTextMessageEncoder;
import com.runescape.game.msg.codec.encoder.InterfaceVisibleMessageEncoder;
import com.runescape.game.msg.codec.encoder.LogoutMessageEncoder;
import com.runescape.game.msg.codec.encoder.MapBlackoutMessageEncoder;
import com.runescape.game.msg.codec.encoder.NpcAnimationMessageEncoder;
import com.runescape.game.msg.codec.encoder.NpcUpdateMessageEncoder;
import com.runescape.game.msg.codec.encoder.PlayerOptionMessageEncoder;
import com.runescape.game.msg.codec.encoder.PlayerUpdateMessageEncoder;
import com.runescape.game.msg.codec.encoder.PrivateChatReceivedMessageEncoder;
import com.runescape.game.msg.codec.encoder.RegionChangeMessageEncoder;
import com.runescape.game.msg.codec.encoder.ResetMinimapFlagMessageEncoder;
import com.runescape.game.msg.codec.encoder.ScriptIntMessageEncoder;
import com.runescape.game.msg.codec.encoder.ScriptMessageEncoder;
import com.runescape.game.msg.codec.encoder.ScriptStringMessageEncoder;
import com.runescape.game.msg.codec.encoder.ServerMessageEncoder;
import com.runescape.game.msg.codec.encoder.SkillMessageEncoder;
import com.runescape.game.msg.codec.encoder.TempMusicMessageEncoder;
import com.runescape.game.msg.codec.encoder.TestMessageEncoder;
import com.runescape.game.msg.codec.encoder.UpdateFriendsMessageEncoder;
import com.runescape.game.util.LandscapeKeyTable;

public final class CodecRepository {

	private final MessageDecoder<?>[] inCodecs = new MessageDecoder<?>[256];
	private final Map<Class<?>, MessageEncoder<?>> outCodecs = new HashMap<>();

	public CodecRepository(LandscapeKeyTable table) {
		/* decoders */
		bind(new PrivateMessageDecoder());
		bind(new RemoveFriendMessageDecoder());
		bind(new AddFriendMessageDecoder());
		bind(new PingMessageDecoder());
		bind(new IdleLogoutMessageDecoder());
		bind(new ButtonMessageDecoder());
		bind(new WalkMessageDecoder(Opcode.WALK));
		bind(new WalkMessageDecoder(207));
		bind(new WalkMessageDecoder(215)); //TODO: check
		bind(new ChatMessageDecoder());
		bind(new CommandMessageDecoder());
		bind(new ExtendedButtonMessageDecoder());
		bind(new SwapItemsMessageDecoder());
		bind(new EquipItemMessageDecoder());
		bind(new DisplayMessageDecoder());
		bind(new RemoveItemMessageDecoder());
		bind(new RegionChangedMessageDecoder());
		bind(new ClickMessageDecoder());
		bind(new FocusMessageDecoder());
		bind(new CameraMessageDecoder());
		bind(new FlagsMessageDecoder());
		bind(new SequenceNumberMessageDecoder());
		bind(new InterfaceClosedMessageDecoder());
		bind(new ObjectOptionOneMessageDecoder());
		bind(new ObjectOptionTwoMessageDecoder());
		bind(new ClickItemMessageDecoder());
		bind(new NpcAttackMessageDecoder());
		bind(new NpcExamineMessageDecoder());
		bind(new ItemOnItemMessageDecoder());
		bind(new MoveItemMessageDecoder());
		bind(new QuickChatMessageDecoder());
		bind(new OperateItemMessageDecoder());
		bind(new DropItemMessageDecoder());
		bind(new ItemOnObjectMessageDecoder());
		bind(new ItemOnPlayerMessageDecoder());
		bind(new ItemExamineMessageDecoder());
		bind(new ObjectExamineMessageDecoder());
		bind(new ClickChatBoxMessageDecoder(Opcode.CLICK_CHAT_BOX));
	//	bind(new ClickChatBoxMessageDecoder(156));
		bind(new MagicOnItemMessageDecoder());
		bind(new MusicPlayingMessageDecoder());
		bind(new MusicVolumeMessageDecoder());
		
		/* encoders */
		bind(new PlayerOptionMessageEncoder());
		bind(new NpcAnimationMessageEncoder());
		bind(new PrivateChatReceivedMessageEncoder());
		bind(new GroundItemMessageEncoder());
		bind(new RegionChangeMessageEncoder(table));
		bind(new InterfaceRootMessageEncoder());
		bind(new InterfaceOpenMessageEncoder());
		bind(new InterfaceCloseMessageEncoder());
		bind(new InterfaceVisibleMessageEncoder());
		bind(new InterfaceTextMessageEncoder());
		bind(new ServerMessageEncoder());
		bind(new LogoutMessageEncoder());
		bind(new PlayerUpdateMessageEncoder());
		bind(new SkillMessageEncoder());
		bind(new EnergyMessageEncoder());
		bind(new InterfaceItemsMessageEncoder());
		bind(new InterfaceSlottedItemsMessageEncoder());
		bind(new InterfaceResetItemsMessageEncoder());
		bind(new ResetMinimapFlagMessageEncoder());
		bind(new ConfigMessageEncoder());
		bind(new ScriptMessageEncoder());
		bind(new NpcUpdateMessageEncoder());
		bind(new ScriptStringMessageEncoder());
		bind(new ScriptIntMessageEncoder());
		bind(new MapBlackoutMessageEncoder());
		bind(new FriendsStatusMessageEncoder());
		bind(new TempMusicMessageEncoder());
		bind(new TestMessageEncoder());
		bind(new ChatSettingsMessageEncoder());
		bind(new UpdateFriendsMessageEncoder());
	}

	public MessageDecoder<?> get(int opcode) {
		return inCodecs[opcode];
	}

	@SuppressWarnings("unchecked")
	public <T extends Message> MessageEncoder<T> get(Class<T> clazz) {
		return (MessageEncoder<T>) outCodecs.get(clazz);
	}

	public void bind(MessageDecoder<?> decoder) {
		inCodecs[decoder.opcode] = decoder;
	}

	public void bind(MessageEncoder<?> encoder) {
		outCodecs.put(encoder.clazz, encoder);
	}

}
