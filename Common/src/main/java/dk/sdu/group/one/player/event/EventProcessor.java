package dk.sdu.group.one.player.event;

public interface EventProcessor<T extends Event> {
    void handleEvent(T event);
}
