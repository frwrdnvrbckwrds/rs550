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
package com.runescape.game.model.region;

import com.runescape.game.cache.MapListener;
import com.runescape.game.model.Position;
import com.runescape.game.model.object.GameObject;
import com.runescape.game.model.object.ObjectType;

public class RegionMapListener implements MapListener {
    private final RegionManager manager;

    public RegionMapListener(RegionManager manager) {
        this.manager = manager;
    }

    @Override
    public void tileDecoded(int flags, Position position) {
        if(!manager.isRegionInitialized(position))
            manager.initializeRegion(position);
    }

    @Override
    public void objectDecoded(int id, int rotation, ObjectType type, Position position) {
        if(!manager.isRegionInitialized(position))
            manager.initializeRegion(position);

        Region region = manager.getRegion(position);
        region.addObject(new GameObject(id, type.getId(), position, rotation));
    }
}
