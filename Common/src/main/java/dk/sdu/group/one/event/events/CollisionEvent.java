package dk.sdu.group.one.event.events;

import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.event.Event;

public class CollisionEvent extends Event{
    Entity e1;
    Entity e2;
    public CollisionDirectionEnum collisionDirection;

    public CollisionEvent(Entity e1, Entity e2, CollisionDirectionEnum collisionDirection, EventType eventType, String msg) {
        super(eventType, msg);
        this.e1 = e1;
        this.e2 = e2;
        this.collisionDirection = collisionDirection;
    }

    public Entity getE1() {
        return e1;
    }

    public Entity getE2() {
        return e2;
    }
}
