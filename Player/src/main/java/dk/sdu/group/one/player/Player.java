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

    ControllerService controllerService;
    private float speed = 2f;

    public Player(){
        super(EntityType.PLAYER, spritePath, 300, 300);
    }
    public Player(String spritePath, float x, float y) {
        super(EntityType.PLAYER, spritePath, x, y, 100);
    }

    @Override
    public void process(EntityManager entityManager, double dt) {

        if(this.controllerService == null){
            controllerService = ServiceLoader.load(ControllerService.class).findFirst().get();
        }

        ControllerScheme controllerScheme = this.controllerService.getInputs();
        Vector2 movement = new Vector2(0, 0);
        if (controllerScheme.isUp()) {
            movement.add(Vector2.up);
            setRadians((float) (Math.PI * 0.5f));
        }
        if (controllerScheme.isDown()) {
            movement.add(Vector2.down);
            setRadians(
                    (float) (Math.PI * 1.5f));
        }
        if (controllerScheme.isLeft()) {
            movement.add(Vector2.left);
            setRadians((float) Math.PI);
        }
        if (controllerScheme.isRight()) {
            movement.add(Vector2.right);
            setRadians(0);
        }
        if (controllerScheme.isStop()) EventBroker.getInstance().publish(new ShootEvent(EventType.ShootEvent, "Shoot", entityManager, this));
        movement = movement.normalize();
        
        this.setX(this.getX() + (float) (movement.getX() * speed * dt));
        this.setY(this.getY() + (float) (movement.getY() * speed * dt));
    }

    @Override
    public void start(MapService mapService, EntityManager entityManager) {
        entityManager.addEntity(new Player(spritePath, 300, 300));
        this.controllerService = ServiceLoader.load(ControllerService.class).findFirst().get();
        entityManager.addEntity(this);
        EventBroker.getInstance().subscribe(EventType.Collision, this);
    }

    @Override
    public void handleEvent(CollisionEvent event) {
        if(event.getE1().equals(this)){
            handleCollision(event.getE2());
        }
        else if(event.getE2().equals(this)){
            handleCollision(event.getE1());
        }
    }

    private void handleCollision(Entity entity){
        if (entity.getType() == EntityType.Weapon){
            Event newEvent = new PickUpEvent(this, entity, EventType.PickUpEvent, "Picked up a gun");
            EventBroker.getInstance().publish(newEvent);
        }
    }
}