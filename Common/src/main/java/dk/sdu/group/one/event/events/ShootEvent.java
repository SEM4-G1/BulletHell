package dk.sdu.group.one.event.events;

import dk.sdu.group.one.event.Event;
public class ShootEvent extends Event {
    public String message;

    public ShootEvent(String message, EventType eventType, String msg) {
        super(eventType, msg);
        this.message = message;
    }
}
