package dk.sdu.group.one.player.event;

import dk.sdu.group.one.player.event.events.EventType;

import java.util.UUID;

public class Event {
    EventType eventType;
    String logMessage;
    UUID uuid = UUID.randomUUID();

    public Event(EventType eventType, String logMessage) {
        this.eventType = eventType;
        this.logMessage = logMessage;
    }
}
