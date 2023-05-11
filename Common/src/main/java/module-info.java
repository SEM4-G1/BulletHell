import dk.sdu.group.one.services.LoggingService;

module Common {
    uses LoggingService;
    exports dk.sdu.group.one.services;
    exports dk.sdu.group.one.data;
    exports dk.sdu.group.one.map;
    exports dk.sdu.group.one.event.events;
    exports dk.sdu.group.one.event;
}