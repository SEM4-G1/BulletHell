package dk.sdu.group.weapon;

import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.event.EventBroker;
import dk.sdu.group.one.event.events.EventType;
import dk.sdu.group.weapon.weaponfacade.WeaponPickUpPart;
import dk.sdu.group.weapon.weaponfacade.WeaponShooterPart;

public abstract class Weapon extends Entity {
    public BulletService bulletService;
    public WeaponPickUpPart pickUpPart = new WeaponPickUpPart(this);
    public WeaponShooterPart weaponShooterPart = new WeaponShooterPart(this);
    public Weapon(EntityType entityType, String spritePath, float x, float y) {
        super(entityType, spritePath, x, y, 20, 20, 100);
    }

    public Weapon() {
        super(EntityType.Weapon, "placeholder", 0, 0, 100);
    }

    public void pickUp(){
        System.out.println("weapon picked up!");
        EventBroker.getInstance().subscribe(EventType.ShootEvent, weaponShooterPart);
    }
    public void drop(){
        System.out.println("weapon dropped");
        EventBroker.getInstance(). unsubscribe(EventType.ShootEvent, weaponShooterPart);
    }
}
