package dk.sdu.group.one.HealthPickUp;

import dk.sdu.group.one.HealthPickUp.HealthPickUpFacade.HealthPickUpPart;
import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.event.EventBroker;
import dk.sdu.group.one.event.events.EventType;
import dk.sdu.group.one.HealthPickUp.HealthPickUp;

import java.util.Random;

public abstract class HealthPickUp extends Entity {
     public HealthPickUpPart healthPickUpPart = new HealthPickUpPart(this);

     private static final String spritePath = "health_pickup.png";
     private int healthRestored;

     public HealthPickUp(EntityType entityType, String spritePath, float x, float y){
         super(entityType, spritePath, x, y);
         healthRestored = (int) (Math.random() * 5 + 1);
     }

     public void pickup(){
         System.out.println("health picked up");
         EventBroker.getInstance().subscribe(EventType.PickUpEvent, healthPickUpPart);
     }
}
