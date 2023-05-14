package dk.sdu.group.one.bullet;

import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.map.MapService;
import dk.sdu.group.weapon.BulletService;

public class PistolBulletProcessor extends Entity implements BulletService {

    private float cooldownMax;
    private float cooldown;

    boolean isRemoved;

    private Entity equipper;

    public PistolBulletProcessor() {
        super(EntityType.BULLET, "placeholder", 0, 0, 1000);
    }

    @Override
    public void process(EntityManager entityManager, double dt){
        cooldown -= dt;
        if (isRemoved){
            entityManager.removeEntity(this);
        }
        int xFromEquipper = 8;
        int yFromEquipper = 8;
        setX(equipper.getX() + 8 + (float) (xFromEquipper * Math.cos(equipper.getRadians())));
        setY(equipper.getY() + (float) (yFromEquipper * Math.sin(equipper.getRadians())));
    }

    @Override
    public void start(MapService mapService, EntityManager entityList){
    }


    @Override
    public void equip(String equippedAsset, float cooldown, EntityManager entityManager, Entity equipper){
        super.setTexturePath(equippedAsset);
        this.cooldownMax = cooldown;
        entityManager.addEntity(this);
        this.equipper = equipper;
    }

    @Override
    public void createBullet(Entity entity, EntityManager entityManager){
        if (cooldown > 0){
            return;
        }
        System.out.println("bullet created");
        cooldown = cooldownMax;
        int entityRadius = 8;
        float x = entity.getX() + (float) (entityRadius * Math.cos(entity.getRadians()));
        float y = entity.getY() + (float) (entityRadius * Math.sin(entity.getRadians()));
        Entity bullet = new PistolBullet(EntityType.BULLET, x, y);
        bullet.setRadians(entity.getRadians());
        System.out.println("bullet created with radians " + entity.getRadians() + "sin(rad) Y : " + Math.sin(entity.getRadians()) + "cos(rad) X :" + Math.cos(entity.getRadians()));
        entityManager.addEntity(bullet);
    }

    @Override
    public void unequip(){
        isRemoved = true;
    }
}