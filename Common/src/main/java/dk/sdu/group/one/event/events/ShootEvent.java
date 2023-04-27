package dk.sdu.group.one.event.events;

import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.event.Event;

public class ShootEvent extends Event {
    public EntityManager entityManager;
    public ShootEvent(EventType eventType, String msg, EntityManager entityManager) {
        super(eventType, msg);

    }
}
