module Weapon {
    uses dk.sdu.group.weapon.BulletService;
    exports dk.sdu.group.weapon.weapontypes;
    exports dk.sdu.group.weapon;
    requires Common;

    provides dk.sdu.group.one.data.Entity with dk.sdu.group.weapon.weapontypes.Pistol;
}