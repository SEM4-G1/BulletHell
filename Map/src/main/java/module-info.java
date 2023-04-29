module Map {
    requires Common;
    exports dk.sdu.group.one.mapmodule;

import dk.sdu.group.one.mapimplementations.LevelManager;
import dk.sdu.group.one.mapimplementations.Map;

module Map {
    requires Common;
    provides dk.sdu.group.one.map.MapService with Map;
    provides dk.sdu.group.one.services.LevelService with LevelManager;
}