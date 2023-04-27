package dk.sdu.group.weapon.weapontypes;

import dk.sdu.group.one.event.EventBroker;
import dk.sdu.group.one.event.events.EventType;
import dk.sdu.group.one.event.events.ShootEvent;
import dk.sdu.group.weapon.BulletService;
import dk.sdu.group.weapon.Weapon;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.map.MapService;
import dk.sdu.group.weapon.weaponfacade.WeaponShooterPart;

import java.util.Scanner;

public class Pistol extends Weapon {
    public static final String spritePath = "gun.png";
    public Pistol(EntityType entityType, float x, float y) {
        super(entityType, spritePath, x, y);
    }

    @Override
    public void process(EntityManager entityManager, double dt) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.nextInt() == 1){
            this.pickUp();
            EventBroker.getInstance().publish(new ShootEvent(EventType.ShootEvent, "pistol shooting", entityManager), EventType.ShootEvent);
        }
    }

    @Override
    public void start(MapService mapService, EntityManager entityList) {
        Pistol pistol = new Pistol(EntityType.Weapon, 0, 0);
        EventBroker.getInstance().subscribe(EventType.PickUpEvent, pistol.pickUpPart);
        entityList.addEntity(pistol);
    }
}
