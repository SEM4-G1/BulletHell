package dk.sdu.group.one.event.events;

import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.event.Event;

public class PickUpEvent extends Event {
    private Entity issuer;
    private Entity target;

    public PickUpEvent(Entity issuer, Entity target,EventType eventType, String msg) {
        super(eventType, msg);
        this.issuer = issuer;
        this.target = target;
    }

    public Entity getIssuer() {
        return issuer;
    }

    public Entity getTarget() {
        return target;
    }
}
