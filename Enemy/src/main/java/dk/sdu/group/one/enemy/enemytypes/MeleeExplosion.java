package dk.sdu.group.one.enemy.enemytypes;

import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.event.Event;
import dk.sdu.group.one.event.EventBroker;
import dk.sdu.group.one.event.EventProcessor;
import dk.sdu.group.one.event.events.CollisionEvent;
import dk.sdu.group.one.event.events.EventType;
import dk.sdu.group.one.event.events.PickUpEvent;
import dk.sdu.group.one.map.MapService;

import java.util.List;

public class MeleeExplosion extends Entity implements EventProcessor<CollisionEvent> {



    private float timer;

    int currentFrame = 1;

    private int damage = 2;

    boolean hasAttacked = false;

    public MeleeExplosion(EntityType entityType, float x, float y) {
        super(EntityType.ENEMY, "earth-explosion-1.png", x, y);
        EventBroker.getInstance().subscribe(EventType.Collision, this);
    }

    @Override
    public void process(EntityManager entityManager, double dt) {
        timer += dt;
        if (timer > 20f){
            timer = 0;
            switch (currentFrame){
                case 1 -> firstFrame();
                case 2 -> secondFrame();
                case 3 -> thirdFrame();
                case 4 -> fourthFrame();
                case 5 -> fifthFrame();
                case 6 -> entityManager.removeEntity(this);
            }
            hasAttacked = false;
            currentFrame++;
        }
    }

    @Override
    public void start(MapService mapService, EntityManager entityList) {

    }

    private void firstFrame() {
        setTexturePath("earth-explosion-1.png");
    }
    private void secondFrame() {
        setTexturePath("earth-explosion-2.png");
    }
    private void thirdFrame() {
        setTexturePath("earth-explosion-3.png");
    }
    private void fourthFrame() {
        setTexturePath("earth-explosion-4.png");
    }
    private void fifthFrame() {
        setTexturePath("earth-explosion-5.png");
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
        if (entity.getType() == EntityType.PLAYER && !hasAttacked){
            int currentHealth = entity.getCurrentHealth();
            entity.setCurrentHealth(currentHealth - damage);
            hasAttacked = true;
        }
    }
}
