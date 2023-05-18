package dk.sdu.group.one.collision;

import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.event.Event;
import dk.sdu.group.one.event.EventBroker;
import dk.sdu.group.one.event.EventProcessor;
import dk.sdu.group.one.event.events.EventType;
import dk.sdu.group.one.map.MapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import dk.sdu.group.one.data.Entity;



public class CollisionTests {
    private static int collisionCounter = 0;

    @BeforeEach
    void resetCounter(){

    }

    @Test
    void squaredCollisionTest() {
        Entity entity1 = new Entity(EntityType.PLAYER, "", 0.0f, 0.0f, 5, 5) {
            @Override
            public void process(EntityManager entityManager, double dt) {}

            @Override
            public void start(MapService mapService, EntityManager entityList) {}
        };

        Entity entity2 = new Entity(EntityType.OBSTACLE, "", 1.0f, 1.0f, 5, 5) {
            @Override
            public void process(EntityManager entityManager, double dt) {}

            @Override
            public void start(MapService mapService, EntityManager entityList) {}
        };

        SquaredCollision squaredCollision = new SquaredCollision();

        assertNotEquals(null, squaredCollision.isColliding(entity1,entity2));
    }

    @Test
    void reflectiveCollision(){
        Entity entity1 = new Entity(EntityType.PLAYER, "", 0.0f, 0.0f, 5, 5) {
            @Override
            public void process(EntityManager entityManager, double dt) {}

            @Override
            public void start(MapService mapService, EntityManager entityList) {}
        };
        EntityManager entityManager = new EntityManager();
        entityManager.addEntity(entity1);

        EventBroker.getInstance().subscribe(EventType.Collision, event -> CollisionTests.collisionCounter++);

        SquaredCollision squaredCollision = new SquaredCollision();
        squaredCollision.postProcess(entityManager);

        assertEquals(0, collisionCounter);
    }

    @Test
    void symmetricCollision(){
        Entity entity1 = new Entity(EntityType.PLAYER, "", 0.0f, 0.0f, 5, 5) {
            @Override
            public void process(EntityManager entityManager, double dt) {}

            @Override
            public void start(MapService mapService, EntityManager entityList) {}
        };

        Entity entity2 = new Entity(EntityType.OBSTACLE, "", 1.0f, 1.0f, 5, 5) {
            @Override
            public void process(EntityManager entityManager, double dt) {}

            @Override
            public void start(MapService mapService, EntityManager entityList) {}
        };
        EntityManager entityManager = new EntityManager();
        entityManager.addEntity(entity1);
        entityManager.addEntity(entity2);

        EventBroker.getInstance().subscribe(EventType.Collision, event -> collisionCounter++));

        SquaredCollision squaredCollision = new SquaredCollision();
        squaredCollision.postProcess(entityManager);

        assertEquals(1, collisionCounter);
    }


}
