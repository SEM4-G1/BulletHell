package dk.sdu.group.one.HealthPickUp;

import dk.sdu.group.one.HealthPickUp.HealthPickUpFacade.HealthPickUpPart;
import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.event.EventBroker;
import dk.sdu.group.one.event.events.EventType;
import dk.sdu.group.one.HealthPickUp.HealthPickUp;
import dk.sdu.group.one.map.MapService;

import java.util.Random;

public class HealthPickUp extends Entity {
     public HealthPickUpPart healthPickUpPart = new HealthPickUpPart(this);

     private static final String spritePath = "health_pickup.png";
     private int healthRestored;

    private boolean isPickedUp;


     @Override
    public void process(EntityManager entityManager, double dt) {
    }

    @Override
    public void start(MapService mapService, EntityManager entityList) {
        HealthPickUp healthPickUp = new HealthPickUp(EntityType.Health, spritePath,0 ,0);
        EventBroker.getInstance().subscribe(EventType.PickUpEvent, healthPickUp.healthPickUpPart);
        entityList.addEntity(healthPickUp);
    }

    public HealthPickUp(EntityType entityType, String spritePath, float x, float y){
         super(entityType, spritePath, x, y);
     }

     public void pickup(){
         System.out.println("health picked up");
         EventBroker.getInstance().subscribe(EventType.PickUpEvent, healthPickUpPart);
         healthRestored = (int) (Math.random() * 5 + 1);

     }
}
