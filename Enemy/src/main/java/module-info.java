module Enemy {
    uses dk.sdu.group.one.enemy.AI.AIservice;
    exports dk.sdu.group.one.enemy.enemytypes;
    exports dk.sdu.group.one.enemy.AI;
    requires Common;
    provides dk.sdu.group.one.data.Entity with dk.sdu.group.one.enemy.enemytypes.Melee;
}