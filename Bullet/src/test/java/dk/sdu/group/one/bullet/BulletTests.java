package dk.sdu.group.one.bullet;

import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.event.events.CollisionDirectionEnum;
import dk.sdu.group.one.event.events.CollisionEvent;
import dk.sdu.group.one.event.events.EventType;
import dk.sdu.group.one.map.MapService;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BulletTests {

    @Test
    public void healthDecreasionTest(){
        PistolBullet pistolBullet = new PistolBullet();

        Entity entity1 = new Entity(EntityType.ENEMY, "", 0.0f, 0.0f, 5, 5, 100) {
            @Override
            public void process(EntityManager entityManager, double dt) {}

            @Override
            public void start(MapService mapService, EntityManager entityList) {}
        };
        entity1.setCurrentHealth(100);
        int startHealth = entity1.getCurrentHealth();

        CollisionEvent collisionEvent = new CollisionEvent(entity1, pistolBullet, CollisionDirectionEnum.LEFT, EventType.Collision, "BulletCollision");

        pistolBullet.handleEvent(collisionEvent);

        int endHealth = entity1.getCurrentHealth();

        assertTrue(startHealth>endHealth);

    }
}
