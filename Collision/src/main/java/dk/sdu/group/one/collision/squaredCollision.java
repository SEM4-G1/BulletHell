
package dk.sdu.group.one.collision;

import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.event.EventBroker;
import dk.sdu.group.one.event.events.CollisionEvent;
import dk.sdu.group.one.event.events.EventType;
import dk.sdu.group.one.services.LoggingService;
import dk.sdu.group.one.services.PostProcessingService;
import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;

import java.text.SimpleDateFormat;
import java.util.ServiceLoader;

public class squaredCollision implements PostProcessingService {
    LoggingService loggingService;

    public squaredCollision() {
        this.loggingService = ServiceLoader.load(LoggingService.class).findFirst().get();
    }

    @Override
    public void postProcess(EntityManager entityManager){
        for (int i = 0; i < entityManager.getEntityList().size(); i++){
            for (int j = i+1; j < entityManager.getEntityList().size(); j++){
                Entity entity1 = entityManager.getEntityList().get(i);
                Entity entity2 = entityManager.getEntityList().get(j);
                if(isColliding(entity1, entity2)) {
                    EventBroker.getInstance().publish(new CollisionEvent(
                            entity1, entity2,
                                    EventType.Collision,  " Entities {" +entity1.toString() + " has collided with " + entity2.toString() + "}"));
                }
                if(entity1.getType() == EntityType.Weapon || entity2.getType() == EntityType.Weapon){
                    loggingService.log(this.getClass(), "Weapon collision detected");
                }
            }
        }
    }

    public boolean isColliding(Entity entity1, Entity entity2) {
        float halfWidth = 8;
        float halfHeight = 8;

        float left1 = entity1.getX() - halfWidth;
        float right1 = entity1.getX() + halfWidth;
        float top1 = entity1.getY() - halfHeight;
        float bottom1 = entity1.getY() + halfHeight;

        float left2 = entity2.getX() - halfWidth;
        float right2 = entity2.getX() + halfWidth;
        float top2 = entity2.getY() - halfHeight;
        float bottom2 = entity2.getY() + halfHeight;

        if (left1 < right2 && right1 > left2 && top1 < bottom2 && bottom1 > top2) {
            return true;
        }

        return false;
    }
}
