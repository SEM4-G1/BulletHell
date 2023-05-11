
package dk.sdu.group.one.collision;

import dk.sdu.group.one.event.EventBroker;
import dk.sdu.group.one.event.events.CollisionDirectionEnum;
import dk.sdu.group.one.event.events.CollisionEvent;
import dk.sdu.group.one.event.events.EventType;
import dk.sdu.group.one.services.PostProcessingService;
import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;

public class SquaredCollision implements PostProcessingService {

    public SquaredCollision() {
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

                CollisionDirectionEnum collisionResult = isColliding(entity1, entity2);

                if(collisionResult != null) {
                    EventBroker.getInstance().publish(new CollisionEvent(
                        entity1,
                        entity2,
                        collisionResult,
                        EventType.Collision,  " Entities {" +entity1.toString() + " has collided with " + entity2.toString() + "}"),
                        EventType.Collision);
                }
            }
        }
    }

    public CollisionDirectionEnum isColliding(Entity entity1, Entity entity2) {
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
            float overlapX = Math.min(right1, right2) - Math.max(left1, left2);
            float overlapY = Math.min(bottom1, bottom2) - Math.max(top1, top2);

            if (overlapX < overlapY) {
                if (left1 > left2) {
                    return CollisionDirectionEnum.LEFT;
                } else {
                    return CollisionDirectionEnum.RIGHT;
                }
            } else {
                if (top1 > top2) {
                    return CollisionDirectionEnum.BOTTOM;
                } else {
                    return CollisionDirectionEnum.TOP;
                }
            }
        }

        return null;
    }
}
