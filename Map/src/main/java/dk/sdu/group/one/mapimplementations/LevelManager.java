package dk.sdu.group.one.mapimplementations;

import dk.sdu.group.one.map.MapService;
import dk.sdu.group.one.services.LevelService;

public class LevelManager implements LevelService {
    MapService currentMap = new Map();
    public LevelManager() {
    }

    @Override
    public MapService getCurrentLevel() {
        return currentMap;
    }
}
