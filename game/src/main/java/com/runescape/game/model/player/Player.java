package com.runescape.game.model.player;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.runescape.game.model.InterfaceSet;
import com.runescape.game.model.Npc;
import com.runescape.game.model.Position;
import com.runescape.game.model.SkillAppearanceListener;
import com.runescape.game.model.SkillMessageListener;
import com.runescape.game.model.World;
import com.runescape.game.model.combat.Combat;
import com.runescape.game.model.friends.Friends;
import com.runescape.game.model.item.Equipment;
import com.runescape.game.model.item.EquipmentDefinition;
import com.runescape.game.model.item.Inventory;
import com.runescape.game.model.item.InventoryAppearanceListener;
import com.runescape.game.model.item.InventoryFullListener;
import com.runescape.game.model.item.InventoryMessageListener;
import com.runescape.game.model.item.Item;
import com.runescape.game.model.mob.Mob;
import com.runescape.game.model.player.appearance.Appearance;
import com.runescape.game.model.trade.Trade;
import com.runescape.game.msg.ChatMessage;
import com.runescape.game.msg.EnergyMessage;
import com.runescape.game.msg.LogoutMessage;
import com.runescape.game.msg.Message;
import com.runescape.game.msg.ServerMessage;
import com.runescape.game.net.game.GameSession;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

public final class Player extends Mob {
	protected Combat combat = new Combat(this);

    public Combat getCombat() {
    	return combat;
    }
	
	private static int appearanceTicketCounter = 0;

	private static int nextAppearanceTicket() {
		if (++appearanceTicketCounter == 0)
			appearanceTicketCounter = 1;

		return appearanceTicketCounter;
	}

	private int databaseId;
	private String username;
	private String password;
	private String email;
	private int rights = 0;
	private Time membershipExpiration;
	private GameSession session;
	private boolean regionChanging;
	private Position lastKnownRegion;
	private final List<Player> localPlayers = new ArrayList<>();
	private final List<Npc> localNpcs = new ArrayList<>();
	private Appearance appearance = new Appearance(this);
	private int energy = 100;
	private final Inventory inventory = new Inventory(28);
	private final Inventory equipment = new Inventory(14);
	private final Inventory bank = new Inventory(496, Inventory.StackMode.ALWAYS);
	private ChatMessage chatMessage;
	private final PlayerSettings settings = new PlayerSettings(this);
	private final InterfaceSet interfaceSet = new InterfaceSet(this);
	private int[] appearanceTickets = new int[World.MAX_PLAYERS];
	private int appearanceTicket = nextAppearanceTicket();
	
	private final Trade trade = new Trade(this);
	private final Friends friends = new Friends();
	
	public Friends getFriends() {
		return friends;
	}
	
	public Trade getTrade() {
		return trade;
	}

	private final Map<String, Object> attributes = new HashMap<>();
	
	public void setAttribute(String key, Object value) {
		attributes.put(key, value);
	}
	
	public Object getAttribute(String key) {
		return attributes.get(key);
	}
	
	public void removeAttribute(String key) {
		attributes.remove(key);
	}

	public Player() {
		init();
	}
	
	public void appearanceUpdated() {
		appearanceTicket = nextAppearanceTicket();
	}

	private void init() {
		skillSet.addListener(new SkillMessageListener(this));
		skillSet.addListener(new SkillAppearanceListener(this));

		inventory.addListener(new InventoryMessageListener(this, 149, 0, 93));
		inventory.addListener(new InventoryFullListener(this, "inventory"));

		bank.addListener(new InventoryFullListener(this, "bank"));

		equipment.addListener(new InventoryMessageListener(this, 387, 29, 94));
		equipment.addListener(new InventoryFullListener(this, "equipment"));
		equipment.addListener(new InventoryAppearanceListener(this));
	}

	public int getDatabaseId() {
		return databaseId;
	}

	public void setDatabaseId(int databaseId) {
		this.databaseId = databaseId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public int getRights() {
		return rights;
	}

	public void setRights(int rights) {
		this.rights = rights;
	}
	
	public boolean getMembership() {
		if (membershipExpiration == null || 
				membershipExpiration.after(new Timestamp(System.currentTimeMillis()))) {
			return false;
		}
		return true;
	}
	
	public Time getMembershipExpiration() {
		return membershipExpiration;
	}

	public void setMembershipExpiration(Time expiration) {
		this.membershipExpiration = expiration;
	}

	public GameSession getSession() {
		return session;
	}
	
	public void setSession(GameSession session) {
		this.session = session;
	}

	public ChannelFuture send(Message message) {
		if (session != null)
			return session.send(message);
		else
			return null;
	}

	public void sendMessage(String text) {
		send(new ServerMessage(text));
	}

	public boolean isRegionChanging() {
		return regionChanging;
	}

	public Position getLastKnownRegion() {
		return lastKnownRegion;
	}

	public void setLastKnownRegion(Position lastKnownRegion) {
		this.lastKnownRegion = lastKnownRegion;
		this.regionChanging = true;
	}

	public List<Player> getLocalPlayers() {
		return localPlayers;
	}

	public List<Npc> getLocalNpcs() {
		return localNpcs;
	}

	public int getAppearanceTicket() {
		return appearanceTicket;
	}

	public int[] getAppearanceTickets() {
		return appearanceTickets;
	}

	public Appearance getAppearance() {
		return appearance;
	}

	public void setAppearance(Appearance appearance) {
		this.appearance = appearance;
		this.appearanceTicket = nextAppearanceTicket();
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
		this.send(new EnergyMessage(energy));
	}

	public ChatMessage getChatMessage() {
		return chatMessage;
	}

	public void setChatMessage(ChatMessage message) {
		this.chatMessage = message;
	}

	public boolean isChatUpdated() {
		return chatMessage != null;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public Inventory getEquipment() {
		return equipment;
	}

	public Inventory getBank() {
		return bank;
	}

	public int getStance() {
		Item weapon = equipment.get(Equipment.WEAPON);
		if (weapon != null)
			return EquipmentDefinition.forId(weapon.getId()).getStance();
		else
			return 1426;
	}

	public PlayerSettings getSettings() {
		return settings;
	}

	public InterfaceSet getInterfaceSet() {
		return interfaceSet;
	}

	public void logout() {
		// TODO this seems fragile
		ChannelFuture future = send(new LogoutMessage());
		if (future != null) {
			future.addListener(ChannelFutureListener.CLOSE);
		}
	}

	@Override
	public void reset() {
		super.reset();
		regionChanging = false;
		chatMessage = null;
	}

	@Override
	public boolean isRunning() {
		return settings.isRunning();
	}

	public boolean faceTo = false;
	
	public boolean isFaceToUpdate() {
		return faceTo;
	}
}