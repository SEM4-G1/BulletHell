package dk.sdu.group.one.player;

import dk.sdu.group.one.player.data.Entity;
import dk.sdu.group.one.player.data.EntityManager;
import dk.sdu.group.one.player.data.EntityType;
import dk.sdu.group.one.player.map.MapService;

public class Player extends Entity {
    public Player(String spritePath, int x, int y) {
        super(EntityType.PLAYER, spritePath, x, y);
    }

    @Override
    public void process(EntityManager entityManager) {

    }

    @Override
    public void start(MapService mapService, EntityManager entityManager) {

    }
}
