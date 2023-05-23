package dk.sdu.group.one.healthpickup;
import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.event.events.EventType;
import dk.sdu.group.one.event.events.PickUpEvent;
import dk.sdu.group.one.healthpickup.HealthPickUpFacade.HealthPickUpPart;
import dk.sdu.group.one.map.MapService;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HealtPickUpTests {

    @Test
    public void healthIncreasionTest(){
        HealthPickUp healthPickUp = new HealthPickUp();
        HealthPickUpPart healthPickUpPart = new HealthPickUpPart(healthPickUp);

        Entity entity1 = new Entity(EntityType.PLAYER, "", 0.0f, 0.0f, 5, 5, 100) {
            @Override
            public void process(EntityManager entityManager, double dt) {}

            @Override
            public void start(MapService mapService, EntityManager entityList) {}
        };
        entity1.setCurrentHealth(1);
        int startHealth = entity1.getCurrentHealth();

        healthPickUpPart.handleEvent(new PickUpEvent(entity1, healthPickUp, EventType.PickUpEvent, "HealthPickUp"));

        int endHealth = entity1.getCurrentHealth();

        assertTrue(startHealth<endHealth);
    }
}
