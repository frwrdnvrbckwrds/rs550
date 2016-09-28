/**
 * scape-emulator-final
 * Copyright (c) 2014 Justin Conner
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in  the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/license/>.
 */
package com.runescape.game.msg.handler;

import com.runescape.game.model.Position;
import com.runescape.game.model.World;
import com.runescape.game.model.mob.WalkingQueue;
import com.runescape.game.model.object.GameObject;
import com.runescape.game.model.player.Player;
import com.runescape.game.msg.ObjectOptionOneMessage;
import com.runescape.game.pf.AStarPathFinder;
import com.runescape.game.pf.Path;
import com.runescape.game.pf.PathFinder;

public class ObjectOptionOneHandler extends MessageHandler<ObjectOptionOneMessage> {
    private final PathFinder pathFinder = new AStarPathFinder();

    @Override
    public void handle(Player player, ObjectOptionOneMessage message) {
        Position position = new Position(message.getX(), message.getY(), player.getPosition().getHeight());
        GameObject object = World.getWorld().getRegionManager().getRegion(position).getObject(position);

        pf_check:
        {
            if(player.getPosition().isWithinDistance(position, 1))
                break pf_check;

            WalkingQueue queue = player.getWalkingQueue();
            Path path = pathFinder.find(player, object);
            if(path != null) {
                Position first = path.poll();
                if(first == null)
                    break pf_check;

                queue.addFirstStep(first);
                queue.setRunningQueue(message.isForceRun());
                player.stopAction();

                while(!path.getPoints().isEmpty()) {
                    Position step = path.poll();
                    queue.addStep(step);
                }
            }
        }
        
        System.out.println("Clicked object:" + object.getDefinition().getName() + " id:" + object.getId() + " x:" + object.getPosition().getX() + " y:" + object.getPosition().getY());
        
        if (object.getDefinition().getName().toLowerCase().contains("bank")) {
        	player.getInterfaceSet().openBank();
        	return;
        }
        
        if (object.getDefinition().getName().toLowerCase().contains("tree")) {
        	//woodcutting
        }
    }
}
