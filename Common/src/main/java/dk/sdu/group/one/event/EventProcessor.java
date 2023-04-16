package dk.sdu.group.one.event;

public interface EventProcessor<T extends Event> {
    void handleEvent(T event);
}
