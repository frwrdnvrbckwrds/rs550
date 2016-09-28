package com.runescape.game.msg.handler;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runescape.game.button.ButtonDispatcher;
import com.runescape.game.command.CommandDispatcher;
import com.runescape.game.model.player.Player;
import com.runescape.game.msg.ButtonMessage;
import com.runescape.game.msg.CameraMessage;
import com.runescape.game.msg.ChatMessage;
import com.runescape.game.msg.ClickChatBoxMessage;
import com.runescape.game.msg.ClickItemMessage;
import com.runescape.game.msg.ClickMessage;
import com.runescape.game.msg.CommandMessage;
import com.runescape.game.msg.DisplayMessage;
import com.runescape.game.msg.DropItemMessage;
import com.runescape.game.msg.EquipItemMessage;
import com.runescape.game.msg.ExtendedButtonMessage;
import com.runescape.game.msg.FlagsMessage;
import com.runescape.game.msg.FocusMessage;
import com.runescape.game.msg.FriendMessage;
import com.runescape.game.msg.IdleLogoutMessage;
import com.runescape.game.msg.InterfaceClosedMessage;
import com.runescape.game.msg.ItemExamineMessage;
import com.runescape.game.msg.ItemOnItemMessage;
import com.runescape.game.msg.ItemOnObjectMessage;
import com.runescape.game.msg.ItemOnPlayerMessage;
import com.runescape.game.msg.MagicOnItemMessage;
import com.runescape.game.msg.Message;
import com.runescape.game.msg.MoveItemMessage;
import com.runescape.game.msg.MusicPlayingMessage;
import com.runescape.game.msg.MusicVolumeMessage;
import com.runescape.game.msg.NpcAttackMessage;
import com.runescape.game.msg.NpcExamineMessage;
import com.runescape.game.msg.ObjectExamineMessage;
import com.runescape.game.msg.ObjectOptionOneMessage;
import com.runescape.game.msg.OperateItemMessage;
import com.runescape.game.msg.PingMessage;
import com.runescape.game.msg.PrivateMessage;
import com.runescape.game.msg.QuickChatMessage;
import com.runescape.game.msg.RegionChangedMessage;
import com.runescape.game.msg.RemoveItemMessage;
import com.runescape.game.msg.SequenceNumberMessage;
import com.runescape.game.msg.SwapItemsMessage;
import com.runescape.game.msg.WalkMessage;
import com.runescape.game.plugin.PluginContext;

public final class MessageDispatcher {

	private static final Logger logger = LoggerFactory.getLogger(MessageDispatcher.class);

	private final Map<Class<?>, MessageHandler<?>> handlers = new HashMap<>();
	private final ButtonDispatcher buttonDispatcher = new ButtonDispatcher();
    private final CommandDispatcher commandDispatcher = new CommandDispatcher();

	public MessageDispatcher() {
		bind(PrivateMessage.class, new PrivateMessageHandler());
		bind(MusicVolumeMessage.class, new MusicVolumeMessageHandler());
		bind(MusicPlayingMessage.class, new MusicPlayingMessageHandler());
		bind(MagicOnItemMessage.class, new MagicOnItemMessageHandler());
		bind(ClickChatBoxMessage.class, new ClickChatBoxMessageHandler());
		bind(ObjectExamineMessage.class, new ObjectExamineMessageHandler());
		bind(ItemExamineMessage.class, new ItemExamineMessageHandler());
		bind(ItemOnPlayerMessage.class, new ItemOnPlayerMessageHandler());
		bind(ItemOnObjectMessage.class, new ItemOnObjectMessageHandler());
		bind(DropItemMessage.class, new DropItemMessageHandler());
		bind(OperateItemMessage.class, new OperateItemMessageHandler());
		bind(ClickItemMessage.class, new ClickItemMessageHandler());
		bind(QuickChatMessage.class, new QuickChatMessageHandler());
		bind(MoveItemMessage.class, new MoveItemMessageHandler());
		bind(ItemOnItemMessage.class, new ItemOnItemMessageHandler());
		bind(NpcExamineMessage.class, new NPCExamineMessageHandler());
		bind(NpcAttackMessage.class, new NpcAttackMessageHandler());
		bind(PingMessage.class, new PingMessageHandler());
		bind(IdleLogoutMessage.class, new IdleLogoutMessageHandler());
		bind(ButtonMessage.class, new ButtonMessageHandler(buttonDispatcher));
		bind(WalkMessage.class, new WalkMessageHandler());
		bind(ChatMessage.class, new ChatMessageHandler());
		bind(CommandMessage.class, new CommandMessageHandler(commandDispatcher));
		bind(ExtendedButtonMessage.class, new ExtendedButtonMessageHandler(buttonDispatcher));
		bind(SwapItemsMessage.class, new SwapItemsMessageHandler());
		bind(EquipItemMessage.class, new EquipItemMessageHandler());
		bind(DisplayMessage.class, new DisplayMessageHandler());
		bind(RemoveItemMessage.class, new RemoveItemMessageHandler());
		bind(RegionChangedMessage.class, new RegionChangedMessageHandler());
		bind(ClickMessage.class, new ClickMessageHandler());
		bind(FocusMessage.class, new FocusMessageHandler());
		bind(CameraMessage.class, new CameraMessageHandler());
		bind(FlagsMessage.class, new FlagsMessageHandler());
		bind(SequenceNumberMessage.class, new SequenceNumberMessageHandler());
		bind(InterfaceClosedMessage.class, new InterfaceClosedMessageHandler());
        bind(ObjectOptionOneMessage.class, new ObjectOptionOneHandler());
        bind(FriendMessage.class, new AddFriendMessageHandler());
	}

	public <T extends Message> void bind(Class<T> clazz, MessageHandler<T> handler) {
		handlers.put(clazz, handler);
	}

	@SuppressWarnings("unchecked")
	public void dispatch(Player player, Message message) {
		MessageHandler<Message> handler = (MessageHandler<Message>) handlers.get(message.getClass());
		if (handler != null) {
			try {
				handler.handle(player, message);
			} catch (Throwable t) {
				logger.warn("Error processing packet.", t);
			}
		} else {
			logger.warn("Cannot dispatch message (no handler): " + message.getClass().getName() + ".");
		}
	}

    public void decorateDispatchers(PluginContext context) {
        context.decorateButtonDispatcher(buttonDispatcher);
        context.decorateCommandDispatcher(commandDispatcher);
    }
}
