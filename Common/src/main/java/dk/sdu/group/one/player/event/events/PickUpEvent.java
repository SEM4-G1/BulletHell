package dk.sdu.group.one.player.event.events;

import dk.sdu.group.one.player.event.Event;

public class PickUpEvent extends Event {
    public int code;

    public PickUpEvent(int code, EventType eventType, String msg) {
        super(eventType, msg);
        this.code = code;
    }
}
