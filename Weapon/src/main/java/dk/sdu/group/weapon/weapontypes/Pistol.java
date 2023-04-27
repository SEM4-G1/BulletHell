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
import java.util.ServiceLoader;

public class Pistol extends Weapon {
    public static final String spritePath = "gun.png";
    public Pistol(EntityType entityType, float x, float y) {
        super(entityType, spritePath, x, y);
        this.bulletService = ServiceLoader.load(BulletService.class).findFirst().get();
        System.out.println("found" + this.bulletService);
    }

    @Override
    public void process(EntityManager entityManager, double dt) {
    }

    @Override
    public void start(MapService mapService, EntityManager entityList) {
        Pistol pistol = new Pistol(EntityType.Weapon, 0, 0);
        EventBroker.getInstance().subscribe(EventType.PickUpEvent, pistol.pickUpPart);
        entityList.addEntity(pistol);
    }
}
