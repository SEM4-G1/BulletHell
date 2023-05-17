package dk.sdu.group.one.player;

import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.event.Event;
import dk.sdu.group.one.event.EventBroker;
import dk.sdu.group.one.event.EventProcessor;
import dk.sdu.group.one.event.events.CollisionEvent;
import dk.sdu.group.one.data.Vector2;
import dk.sdu.group.one.event.events.EventType;
import dk.sdu.group.one.event.events.PickUpEvent;
import dk.sdu.group.one.event.events.ShootEvent;
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
    private float speed = 2f;

    public Player(){
        super(EntityType.PLAYER, spritePath, 200, 200, 20, 20);
    }
    public Player(String spritePath, float x, float y) {
        super(EntityType.PLAYER, spritePath, x, y, 20, 20, 100);
    }

    @Override
    public void process(EntityManager entityManager, double dt) {
        if(this.controllerService == null){
            controllerService = ServiceLoader.load(ControllerService.class).findFirst().get();
        }

        ControllerScheme controllerScheme = this.controllerService.getInputs();
        Vector2 movement = new Vector2(0, 0);
        if (controllerScheme.isUp() && !this.isCollidingUp) {
            movement.add(Vector2.up);
            setRadians((float) (Math.PI * 0.5f));
            resetCollisionStates();
        }
        if (controllerScheme.isDown() && !this.isCollidingDown) {
            movement.add(Vector2.down);
            setRadians(
                    (float) (Math.PI * 1.5f));
            resetCollisionStates();
        }
        if (controllerScheme.isLeft() && !this.isCollidingLeft) {
            movement.add(Vector2.left);
            setRadians((float) Math.PI);
            resetCollisionStates();
        }
        if (controllerScheme.isRight() && !this.isCollidingRight) {
            movement.add(Vector2.right);
            setRadians(0);
            resetCollisionStates();
        }
        if (controllerScheme.isStop()) EventBroker.getInstance().publish(new ShootEvent(EventType.ShootEvent, "Shoot", entityManager, this));
        movement = movement.normalize();

        this.setX(this.getX() + (float) (movement.getX() * speed * dt));
        this.setY(this.getY() + (float) (movement.getY() * speed * dt));
        if(getCurrentHealth() <= 0){
            entityManager.removeEntity(this);
        }
    }

    @Override
    public void start(MapService mapService, EntityManager entityManager) {
        Player player = new Player(this.spritePath, 40, 40);
        player.controllerService = ServiceLoader.load(ControllerService.class).findFirst().get();
        entityManager.addEntity(player);
        EventBroker.getInstance().subscribe(EventType.Collision, player);
        System.out.println("player subscribed to collision events");
    }

    @Override
    public void handleEvent(CollisionEvent event) {
        if(event.getE1().equals(this)){
            handleCollision(event.getE2());
        }
        else if(event.getE2().equals(this)){
            handleCollision(event.getE1());
        }

        // Only respond to collisions with other entities
        if (event.getE1().equals(this) && !event.getE2().equals(this)) {
            switch (event.collisionDirection) {
                case TOP -> this.isCollidingUp = true;
                case BOTTOM -> this.isCollidingDown = true;
                case LEFT -> this.isCollidingLeft = true;
                case RIGHT -> this.isCollidingRight = true;
            }
            System.out.println("Collision with " + event.getE1().getType() + " on " + event.collisionDirection);
        }
    }

    private void handleCollision(Entity entity){
        if (entity.getType() == EntityType.WEAPON){
            Event newEvent = new PickUpEvent(this, entity, EventType.PickUpEvent, "Picked up a gun");
            EventBroker.getInstance().publish(newEvent);
            System.out.println("Player picked up a gun");
        }
        if (entity.getType() == EntityType.Health){
            Event newEvent = new PickUpEvent(this, entity, EventType.PickUpEvent, "Picked up a health pack");
            EventBroker.getInstance().publish(newEvent);
            System.out.println("Player picked up a health pack");
        }
    }

    private void resetCollisionStates() {
        this.isCollidingUp = false;
        this.isCollidingDown = false;
        this.isCollidingLeft = false;
        this.isCollidingRight = false;
    }
}
