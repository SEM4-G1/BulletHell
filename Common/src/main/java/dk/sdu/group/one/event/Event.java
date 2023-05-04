package dk.sdu.group.one.event;

import dk.sdu.group.one.event.events.EventType;

import java.text.SimpleDateFormat;

import java.util.UUID;

public class Event {
    EventType eventType;
    String logMessage;
    UUID uuid = UUID.randomUUID();

    public Event(EventType eventType, String logMessage){
        this.eventType = eventType;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("timestamp: ");
        stringBuilder.append(new SimpleDateFormat("HH:mm:ss.SSS'ms'").format(new java.util.Date()));
        stringBuilder.append(" event type: ");
        stringBuilder.append(eventType.name());
        stringBuilder.append(" message: ");
        stringBuilder.append(logMessage);
        this.logMessage = stringBuilder.toString();
    }
}
