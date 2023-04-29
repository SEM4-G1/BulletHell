package dk.sdu.group.one.player;

import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.map.MapService;

public class Player extends Entity {
    private static final String spritePath = "player.png";
    private int speed = 100;
    public Player(){
        super(EntityType.PLAYER, spritePath, 1, 1);
    }
    public Player(String spritePath, float x, float y) {
        super(EntityType.PLAYER, spritePath, x, y, 100);
    }

    @Override
    public void process(EntityManager entityManager, double dt) {
        this.setX((float) (this.getX() + speed*dt));
        this.setY((float) (this.getY() + speed*dt));
    }

    @Override
    public void start(MapService mapService, EntityManager entityManager) {
        entityManager.addEntity(new Player(spritePath, 100, 100));
    }
}
