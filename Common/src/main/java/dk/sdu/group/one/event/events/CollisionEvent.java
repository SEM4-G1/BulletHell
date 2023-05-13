package dk.sdu.group.one.event.events;

import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.event.Event;

public class CollisionEvent extends Event{
    private Entity e1;
    private Entity e2;

    public CollisionEvent(Entity e1, Entity e2, EventType eventType, String msg) {
        super(eventType, msg);
        this.e1 = e1;
        this.e2 = e2;
    }

    public Entity getE1() {
        return e1;
    }

    public Entity getE2() {
        return e2;
    }
}
