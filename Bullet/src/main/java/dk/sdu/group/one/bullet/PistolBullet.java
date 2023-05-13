package dk.sdu.group.one.bullet;

import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.map.MapService;
import dk.sdu.group.weapon.BulletService;

public class PistolBullet extends Entity implements BulletService {
    double speed = 5f;
    public static final String assetPath = "bullet.png";
    public PistolBullet() {
        super(EntityType.BULLET, assetPath, 0, 0,
                10);
    }
    public PistolBullet(EntityType entityType, float x, float y) {
        super(entityType, assetPath, x, y, 10);
    }

    @Override
    public void createBullet(Entity entity, EntityManager entityManager) {
        int entityRadius = 8;
        float x = entity.getX() + (float) (entityRadius * Math.cos(entity.getRadians()));
        float y = entity.getY() + (float) (entityRadius * Math.sin(entity.getRadians()));
        Entity bullet = new PistolBullet(EntityType.BULLET, x, y);
        bullet.setRadians(entity.getRadians());
        System.out.println("bullet created with radians " + entity.getRadians() + "sin(rad) Y : " + Math.sin(entity.getRadians()) + "cos(rad) X :" + Math.cos(entity.getRadians()));
        entityManager.addEntity(bullet);
    }

    @Override
    public void process(EntityManager entityManager, double dt) {
        int screenSize = 1000;
        if (this.getX() > screenSize || this.getX() < 0 || this.getY() > screenSize || this.getY() < 0){
            entityManager.removeEntity(this);
        }
        applySpeed(dt);
    }

    private void applySpeed(double dt){
        setX(getX() + (float) (speed * Math.cos(getRadians()) * dt));
        setY(getY() + (float) (speed * Math.sin(getRadians()) * dt));
    }

    @Override
    public void start(MapService mapService, EntityManager entityList) {
    }
}
