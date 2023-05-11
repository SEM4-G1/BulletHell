package dk.sdu.group.one.player;

import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.event.EventBroker;
import dk.sdu.group.one.event.EventProcessor;
import dk.sdu.group.one.event.events.CollisionEvent;
import dk.sdu.group.one.data.Vector2;
import dk.sdu.group.one.event.events.EventType;
import dk.sdu.group.one.player.ControllerService.ControllerScheme;
import dk.sdu.group.one.map.MapService;

import java.util.ServiceLoader;

public class Player extends Entity implements EventProcessor<CollisionEvent>{
    private static final String spritePath = "player.png";
    private Boolean isCollidingLeft = false;
    private Boolean isCollidingRight = false;
    private Boolean isCollidingUp = false;
    private Boolean isCollidingDown = false;

    ControllerService controllerService;
    private float speed = 0.01f;

    public Player(){
        super(EntityType.PLAYER, spritePath, 200, 200, 20, 20);
    }
    public Player(String spritePath, float x, float y) {
        super(EntityType.PLAYER, spritePath, x, y, 20, 20, 100);
    }

    @Override
    public void process(EntityManager entityManager, double dt) {
        ControllerScheme controllerScheme = this.controllerService.getInputs();
        Vector2 movement = new Vector2(0, 0);

        // If the player collides with something, the "un"-collide when moving in the opposite direction
        if (controllerScheme.isUp() && !this.isCollidingUp) {
            movement.add(Vector2.up);
            resetCollisionStates();
        }
        if (controllerScheme.isDown() && !this.isCollidingDown) {
            movement.add(Vector2.down);
            resetCollisionStates();
        }
        if (controllerScheme.isLeft() && !this.isCollidingLeft) {
            movement.add(Vector2.left);
            resetCollisionStates();
        }
        if (controllerScheme.isRight() && !this.isCollidingRight) {
            movement.add(Vector2.right);
            resetCollisionStates();
        }

        this.setX(this.getX() + (float)Math.floor(movement.getX() + speed * dt));
        this.setY(this.getY() + (float)Math.floor(movement.getY() + speed * dt));
    }

    @Override
    public void start(MapService mapService, EntityManager entityManager) {
        EventBroker.getInstance().subscribe(EventType.Collision, this);
        this.controllerService = ServiceLoader.load(ControllerService.class).findFirst().get();
        entityManager.addEntity(this);
    }

    @Override
    public void handleEvent(CollisionEvent event) {
        // Only respond to collisions with other entities
        if (event.e1.getClass() == Player.class && event.e2.getClass() != Player.class) {
            switch (event.collisionDirection) {
                case TOP -> this.isCollidingUp = true;
                case BOTTOM -> this.isCollidingDown = true;
                case LEFT -> this.isCollidingLeft = true;
                case RIGHT -> this.isCollidingRight = true;
            }
            System.out.println("Collision with " + event.e2.getType() + " on " + event.collisionDirection);
        }
    }

    private void resetCollisionStates() {
        this.isCollidingUp = false;
        this.isCollidingDown = false;
        this.isCollidingLeft = false;
        this.isCollidingRight = false;
    }
}
