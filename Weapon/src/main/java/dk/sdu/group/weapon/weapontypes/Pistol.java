package dk.sdu.group.weapon.weapontypes;

import dk.sdu.group.one.event.EventBroker;
import dk.sdu.group.one.event.events.EventType;
import dk.sdu.group.weapon.Weapon;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.map.MapService;

public class Pistol extends Weapon {
    public Pistol(EntityType entityType, String spritePath, float x, float y) {
        super(entityType, spritePath, x, y);
    }

    @Override
    public void process(EntityManager entityManager, double dt) {

    }

    @Override
    public void start(MapService mapService, EntityManager entityList) {
        Pistol pistol = new Pistol(EntityType.Weapon, "assets/pistol.png", 0, 0);
        EventBroker.getInstance().subscribe(EventType.PickUpEvent, pistol.pickUpPart);
        entityList.addEntity(pistol);
    }

    @Override
    public void Start(MapService mapService, EntityManager entityManager) {

    }
}
