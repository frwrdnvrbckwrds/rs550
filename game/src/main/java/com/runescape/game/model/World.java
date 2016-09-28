package com.runescape.game.model;

import java.util.ArrayList;
import java.util.List;

import com.runescape.game.model.player.Player;
import com.runescape.game.model.region.RegionManager;
import com.runescape.game.net.game.GameSession;
import com.runescape.game.pf.TraversalMap;
import com.runescape.game.task.Scheduler;
import com.runescape.game.update.PlayerUpdater;

public final class World {

	public static final int MAX_PLAYERS = 2000;

	private static final World world = new World();

	public static World getWorld() {
		return world;
	}

	private final MobList<Player> players = new MobList<>(MAX_PLAYERS);
	private final MobList<Npc> npcs = new MobList<>(32000);
	private final List<GroundItem> groundItems = new ArrayList<>();
	private final Scheduler taskScheduler = new Scheduler();
	private final PlayerUpdater updater = new PlayerUpdater(this);
    private final RegionManager regionManager = new RegionManager();

    private final TraversalMap traversalMap;

	private World() {
        this.traversalMap = new TraversalMap(regionManager);
	}

	public MobList<Player> getPlayers() {
		return players;
	}

	public MobList<Npc> getNpcs() {
		return npcs;
	}
	
	public List<GroundItem> getWorldItems() {
		return groundItems;
	}

	public Scheduler getTaskScheduler() {
		return taskScheduler;
	}

    public TraversalMap getTraversalMap() {
        return traversalMap;
    }

    public RegionManager getRegionManager() {
        return regionManager;
    }

	public void tick() {
		for (Player player : players) {
			GameSession session = player.getSession();
			if (session != null)
				session.processMessageQueue();
		}

		taskScheduler.pulse();
		updater.tick();
	}

	public Player getPlayerByName(String username) {
		for (Player player : players) {
			if (player.getUsername().equals(username))
				return player;
		}
		return null;
	}
}
