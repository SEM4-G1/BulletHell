package dk.sdu.group.one.bullet;

import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.map.MapService;
import dk.sdu.group.weapon.BulletService;

public class PistolBullet extends Entity implements BulletService {
    double speed = 5;
    final String assetPath = "bullet.png";

    public PistolBullet(EntityType entityType, String spritePath, float x, float y) {
        super(entityType, spritePath, x, y, 10);
    }

    @Override
    public void createBullet(Entity entity, EntityManager entityManager) {
        float x = entity.getX();
        float y = entity.getY();
        x *= Math.cos(entity.getRadians());
        y *= Math.sin(entity.getRadians());
        Entity bullet = new PistolBullet(EntityType.BULLET, assetPath, x, y);
        bullet.setRadians(entity.getRadians());
        entityManager.addEntity(bullet);
    }

    @Override
    public void process(EntityManager entityManager, double dt) {
        for (Entity entity : entityManager.getEntityList()){
            if (entity instanceof PistolBullet pistolBullet){
                applySpeed(dt, pistolBullet);
            }
        }
    }

    private void applySpeed(double dt, PistolBullet bullet){
        bullet.setX((float) (bullet.getX()+speed*dt));
        bullet.setY((float) (bullet.getY()+speed*dt));
    }

    @Override
    public void start(MapService mapService, EntityManager entityList) {

    }

    @Override
    public void Start(MapService mapService, EntityManager entityManager) {

    }
}
