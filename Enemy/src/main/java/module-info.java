module Enemy {
    exports dk.sdu.group.one.enemy.enemytypes;
    requires Common;
    requires AI.AStar;
    provides dk.sdu.group.one.data.Entity with dk.sdu.group.one.enemy.enemytypes.Melee;
}