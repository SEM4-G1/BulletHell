package dk.sdu.group.one.enemy.enemytypes;

import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.enemy.Enemy;
import dk.sdu.group.one.map.MapService;

public class Melee extends Enemy {

    public Melee(String spritePath, int x, int y) {
        super(spritePath, x, y);
    }

    @Override
    public void start(MapService mapService, EntityManager entityList) {

    }
}
