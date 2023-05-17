package dk.sdu.group.one.bullet;

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
import dk.sdu.group.weapon.BulletService;

public class PistolBullet extends Entity implements EventProcessor<CollisionEvent>{
    double speed = 5f;

    int damage = 20;

    public static final String assetPath = "bulle" + "t.png";

    public PistolBullet(){
        super(EntityType.BULLET, assetPath, 0, 0, 10, 10, 10);
    }

    public PistolBullet(EntityType entityType, float x, float y){
        super(entityType, assetPath, x, y, 10, 10, 10);
        EventBroker.getInstance().subscribe(EventType.Collision, this);
    }

    @Override
    public void process(EntityManager entityManager, double dt){
        int screenSize = 1000;
        if (this.getX() > screenSize || this.getX() < 0 || this.getY() > screenSize || this.getY() < 0){
            entityManager.removeEntity(this);
        }
        applySpeed(dt);
        if (this.getCurrentHealth() <= 0){
            entityManager.removeEntity(this);
        }
    }

    private void applySpeed(double dt){
        setX(getX() + (float) (speed * Math.cos(getRadians()) * dt));
        setY(getY() + (float) (speed * Math.sin(getRadians()) * dt));
    }

    @Override
    public void start(MapService mapService, EntityManager entityList){
    }

    @Override
    public void handleEvent(CollisionEvent event){
        if(event.getE1().equals(this)){
            handleCollision(event.getE2());
        }
        else if(event.getE2().equals(this)){
            handleCollision(event.getE1());
        }
    }

    private void handleCollision(Entity entity){
        if (entity.getType() == EntityType.PLAYER || entity.getClass() == PistolBulletProcessor.class){
            return;
        }
        if (entity.getType() == EntityType.OBSTACLE){
            this.setCurrentHealth(0);
            System.out.println(this.getCurrentHealth());
            return;
        }
        int hp = entity.getCurrentHealth();
        entity.setCurrentHealth(hp - damage);
        this.setCurrentHealth(0);
    }
}