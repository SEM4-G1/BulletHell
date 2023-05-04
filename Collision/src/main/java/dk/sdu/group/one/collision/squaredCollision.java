
package dk.sdu.group.one.collision;

import dk.sdu.group.one.event.EventBroker;
import dk.sdu.group.one.event.events.CollisionDirectionEnum;
import dk.sdu.group.one.event.events.CollisionEvent;
import dk.sdu.group.one.event.events.EventType;
import dk.sdu.group.one.services.PostProcessingService;
import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;

import java.text.SimpleDateFormat;

public class squaredCollision implements PostProcessingService {

    public squaredCollision() {
    }

    @Override
    public void postProcess(EntityManager entityManager){
        for (Entity entity1 : entityManager.getEntityList()){
            for (Entity entity2 : entityManager.getEntityList()){
                if (entity1 == entity2) {
                    continue;
                }

                // Ex. rocks with rocks
                if (entity1.getClass() == entity2.getClass()) {
                    continue;
                }

                if(isColliding(entity1, entity2)) {
                    EventBroker.getInstance().publish(new CollisionEvent(
                        entity1,
                        entity2,
                        calculateCollisionDirection(entity1, entity2),
                        EventType.Collision,  " Entities {" +entity1.toString() + " has collided with " + entity2.toString() + "}"),
                        EventType.Collision);
                }
            }
        }
    }

    public CollisionDirectionEnum calculateCollisionDirection(Entity entity1, Entity entity2) {
        float halfWidth1 = entity1.getWidth() / 2;
        float halfHeight1 = entity1.getHeight() / 2;
        float halfWidth2 = entity2.getWidth() / 2;
        float halfHeight2 = entity2.getHeight() / 2;

        float dx = entity1.getX() - entity2.getX();
        float dy = entity1.getY() - entity2.getY();

        float overlapX = halfWidth1 + halfWidth2 - Math.abs(dx);
        float overlapY = halfHeight1 + halfHeight2 - Math.abs(dy);

        if (overlapX < overlapY) {
            if (dx > 0) {
                return CollisionDirectionEnum.LEFT;
            } else {
                return CollisionDirectionEnum.RIGHT;
            }
        } else {
            if (dy > 0) {
                return CollisionDirectionEnum.TOP;
            } else {
                return CollisionDirectionEnum.BOTTOM;
            }
        }
    }

    public boolean isColliding(Entity entity1, Entity entity2) {
        float halfWidth1 = entity1.getWidth() / 2;
        float halfHeight1 = entity1.getHeight() / 2;
        float halfWidth2 = entity2.getWidth() / 2;
        float halfHeight2 = entity2.getHeight() / 2;

        float left1 = entity1.getX() - halfWidth1;
        float right1 = entity1.getX() + halfWidth1;
        float top1 = entity1.getY() - halfHeight1;
        float bottom1 = entity1.getY() + halfHeight1;

        float left2 = entity2.getX() - halfWidth2;
        float right2 = entity2.getX() + halfWidth2;
        float top2 = entity2.getY() - halfHeight2;
        float bottom2 = entity2.getY() + halfHeight2;

        if (left1 < right2 && right1 > left2 && top1 < bottom2 && bottom1 > top2) {
            return true;
        }

        return false;
    }
}
