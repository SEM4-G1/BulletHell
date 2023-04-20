package dk.sdu.group.one.event;


import java.util.*;

import dk.sdu.group.one.event.events.EventType;

public class EventBroker{

    private static EventBroker eventBroker;
    public static EventBroker getInstance(){
        if (eventBroker == null){
            eventBroker = new EventBroker();
        }
        return eventBroker;
    }

    private Map<EventType, Event> events = new HashMap<>();

    private final Map<EventType, Set<EventProcessor<? extends Event>>> subscribers = new EnumMap<>(EventType.class);

    @SuppressWarnings("unchecked")
    public <T extends Event> void publish(T event, EventType eventType) {
        events.put(eventType, event);
        for (EventProcessor<?> processor : subscribers.get(eventType)) {
            ((EventProcessor<T>) processor).handleEvent(event);
        }
    }

    public EventBroker() {
        for (EventType eventType : EventType.values()) {
            subscribers.put(eventType, new HashSet<>());
        }
    }

    public <T extends Event> void subscribe(EventType eventType, EventProcessor<T> subscriber) {
        subscribers.get(eventType).add(subscriber);
    }

    public <T extends Event> void unsubscribe(EventType eventType, EventProcessor<T> subscriber) {
        subscribers.get(eventType).remove(subscriber);
    }

}
