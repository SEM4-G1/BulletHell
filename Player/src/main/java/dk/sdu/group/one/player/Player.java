package dk.sdu.group.one.player;

import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.map.MapService;

public class Player extends Entity {
    public Player(String spritePath, int x, int y) {
        super(EntityType.PLAYER, spritePath, x, y, 1);
    }

    @Override
    public void process(EntityManager entityManager) {

    }

    @Override
    public void start(MapService mapService, EntityManager entityManager) {

    }
}
