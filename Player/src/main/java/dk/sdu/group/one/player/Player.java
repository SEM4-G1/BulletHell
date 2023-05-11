package dk.sdu.group.one.player;

import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.event.EventProcessor;
import dk.sdu.group.one.event.events.CollisionEvent;
import dk.sdu.group.one.data.Vector2;
import dk.sdu.group.one.player.ControllerService.ControllerScheme;
import dk.sdu.group.one.map.MapService;

import java.util.ServiceLoader;

public class Player extends Entity implements EventProcessor<CollisionEvent>{
    private static final String spritePath = "player.png";

    ControllerService controllerService;
    private float speed = 2f;

    public Player(){
        super(EntityType.PLAYER, spritePath, 200, 200);
    }
    public Player(String spritePath, float x, float y) {
        super(EntityType.PLAYER, spritePath, x, y, 100);
    }

    @Override
    public void process(EntityManager entityManager, double dt) {
        ControllerScheme controllerScheme = this.controllerService.getInputs();
        Vector2 movement = new Vector2(0, 0);
        if (controllerScheme.isUp()) movement.add(Vector2.up);
        if (controllerScheme.isDown()) movement.add(Vector2.down);
        if (controllerScheme.isLeft()) movement.add(Vector2.left);
        if (controllerScheme.isRight()) movement.add(Vector2.right);
        movement = movement.normalize();

        Double movex = movement.getX() * speed * dt;
        Double movey = movement.getY() * speed * dt;
        this.setX(this.getX() + movex.floatValue());
        this.setY(this.getY() + movey.floatValue());
    }

    @Override
    public void start(MapService mapService, EntityManager entityManager) {
        System.out.println(this.hashCode());
        this.controllerService = ServiceLoader.load(ControllerService.class).findFirst().get();
        entityManager.addEntity(this);
    }

    @Override
    public void handleEvent(CollisionEvent event) {
        System.out.println("I Have collided");
    }
}
