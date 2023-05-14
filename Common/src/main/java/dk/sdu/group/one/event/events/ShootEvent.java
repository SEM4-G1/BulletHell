package dk.sdu.group.one.event.events;

import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.event.Event;

public class ShootEvent extends Event {
    public EntityManager entityManager;
    private Entity issuer;
    public ShootEvent(EventType eventType, String msg, EntityManager entityManager, Entity issuer){
        super(eventType, msg);
        this.entityManager = entityManager;
        this.issuer = issuer;
    }

    public Entity getIssuer() {
        return issuer;
    }
}