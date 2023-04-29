
module Map {
    requires Common;
    provides dk.sdu.group.one.map.MapService with dk.sdu.group.one.mapimplementations.Map;
    provides dk.sdu.group.one.services.LevelService with dk.sdu.group.one.mapimplementations.LevelManager;
}