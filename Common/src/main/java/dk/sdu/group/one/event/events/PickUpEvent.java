package dk.sdu.group.one.event.events;

import dk.sdu.group.one.event.Event;

public class PickUpEvent extends Event {
    public int code;

    public PickUpEvent(int code) {
        this.code = code;
    }
}
