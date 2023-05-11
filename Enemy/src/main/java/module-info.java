module Enemy {
    exports dk.sdu.group.one.enemy.enemytypes;
    requires Common;
    provides dk.sdu.group.one.data.Entity with dk.sdu.group.one.enemy.enemytypes.Melee;
}