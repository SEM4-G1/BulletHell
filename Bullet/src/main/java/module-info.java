module Bullet {
    requires Common;
    requires Weapon;
    exports dk.sdu.group.one.bullet;
    provides dk.sdu.group.weapon.BulletService with dk.sdu.group.one.bullet.PistolBulletProcessor;
}
