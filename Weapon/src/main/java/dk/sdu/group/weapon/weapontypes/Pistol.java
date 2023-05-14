package dk.sdu.group.weapon.weapontypes;

import dk.sdu.group.one.event.EventBroker;
import dk.sdu.group.one.event.events.EventType;
import dk.sdu.group.one.event.events.ShootEvent;
import dk.sdu.group.one.map.Coordinate;
import dk.sdu.group.weapon.BulletService;
import dk.sdu.group.weapon.Weapon;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.map.MapService;
import dk.sdu.group.weapon.weaponfacade.WeaponShooterPart;

import java.util.Scanner;
import java.util.ServiceLoader;

public class Pistol extends Weapon{
    public static final String spritePath = "gun.png";
    public static final String equippedAsset = "equipped-pistol.png";
    public Pistol(float x, float y) {
        super(EntityType.WEAPON, spritePath, x, y);
        this.bulletService = ServiceLoader.load(BulletService.class).findFirst().get();
    }

    public Pistol(){
        super();
    }

    @Override
    public void process(EntityManager entityManager, double dt){
        if (this.pickUpPart.isPickedUp()){
            bulletService.equip(equippedAsset, 50f, entityManager, pickUpPart.getPickedUpBy());
            entityManager.removeEntity(this);
            System.out.println("pistol equipped and removed from world");
        }
    }

    @Override
    public void start(MapService mapService, EntityManager entityList){
        int cellWidth = (int) (480.0f/mapService.getWidth());
        int cellHeight = (int) (480.0f/mapService.getHeight());
        for (int i = 0; i < 1; i++){
            Pistol pistol = new Pistol( 20, 20);
            Coordinate melee_coordinate = new Coordinate((int)(Math.random()*mapService.getWidth()),(int)(Math.random()*mapService.getHeight()));
            while (!isUnique(entityList, cellWidth, cellHeight, melee_coordinate)){
                melee_coordinate = new Coordinate((int)(Math.random()*mapService.getWidth()),(int)(Math.random()*mapService.getHeight()));
            }

            pistol.setX(melee_coordinate.getX() * cellWidth);
            pistol.setY(melee_coordinate.getY() * cellHeight);
            entityList.addEntity(pistol);
            EventBroker.getInstance().subscribe(EventType.PickUpEvent, pistol.pickUpPart);
        }

    }
    private boolean isUnique(EntityManager entityList, float cellWidth, float cellHeight, Coordinate final_coordinate) {
        return entityList.getEntityList().stream()
                .filter(entity -> entity.getX() == final_coordinate.getX() * cellWidth
                        &&
                        entity.getY() == final_coordinate.getY() * cellHeight)
                .findFirst().isEmpty();
    }
}