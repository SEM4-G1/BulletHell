package dk.sdu.group.one.bullet;

import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.map.MapService;
import dk.sdu.group.weapon.BulletService;

public class PistolBullet extends Entity implements BulletService {
    double speed = 5;
    final String assetPath = "bullet.png";

    public PistolBullet(EntityType entityType, String spritePath, int x, int y) {
        super(entityType, spritePath, x, y);
    }

    @Override
    public void createBullet(Entity entity, EntityManager entityManager) {
        int x = entity.getX();
        int y = entity.getY();
        Entity bullet = new PistolBullet(EntityType.BULLET, assetPath, x, y);
        bullet.setRadians(entity.getRadians());
        entityManager.addEntity(bullet);
    }

    @Override
    public void process(EntityManager entityManager) {

    }

    @Override
    public void start(MapService mapService, EntityManager entityList) {

    }
}
