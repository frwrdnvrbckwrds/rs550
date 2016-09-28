package com.runescape.game.msg.handler;

import com.runescape.game.model.Npc;
import com.runescape.game.model.Position;
import com.runescape.game.model.mob.WalkingQueue;
import com.runescape.game.model.player.Player;
import com.runescape.game.msg.NpcAttackMessage;
import com.runescape.game.pf.AStarPathFinder;
import com.runescape.game.pf.Path;
import com.runescape.game.pf.PathFinder;

public class NpcAttackMessageHandler extends MessageHandler<NpcAttackMessage> {
	private final PathFinder pathFinder = new AStarPathFinder();
	 
	@Override
	public void handle(Player player, NpcAttackMessage message) {
		Npc npc = message.getNpc();
		pf_check:
        {
            if(player.getPosition().isWithinDistance(npc.getPosition(), 1))
                break pf_check;

            WalkingQueue queue = player.getWalkingQueue();
            Path path = pathFinder.find(player, npc.getPosition().getX(), npc.getPosition().getY());
            if(path != null) {
                Position first = path.poll();
                if(first == null)
                    break pf_check;
                
                queue.addFirstStep(first);
                player.stopAction();

                while(!path.getPoints().isEmpty()) {
                    Position step = path.poll();
                    queue.addStep(step);
                }
            }
        }
	}

}
