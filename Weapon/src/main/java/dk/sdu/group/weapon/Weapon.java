package dk.sdu.group.weapon;

import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.event.EventBroker;
import dk.sdu.group.one.event.events.EventType;
import dk.sdu.group.weapon.weaponfacade.WeaponPickUpPart;
import dk.sdu.group.weapon.weaponfacade.WeaponShooterPart;

public abstract class Weapon extends Entity {
    public WeaponPickUpPart pickUpPart = new WeaponPickUpPart(this);
    public WeaponShooterPart weaponShooterPart = new WeaponShooterPart();
    public Weapon(EntityType entityType, String spritePath, int x, int y) {
        super(entityType, spritePath, x, y);
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