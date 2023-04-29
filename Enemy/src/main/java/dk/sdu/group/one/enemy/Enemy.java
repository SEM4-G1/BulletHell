package dk.sdu.group.one.enemy;

import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.map.MapService;

public abstract class Enemy extends Entity{
    public MapService mapService;
    public Enemy(String spritePath, int x, int y, MapService mapService){
        super(EntityType.ENEMY, spritePath, x, y, 2);
        this.mapService = mapService;
    }
}
