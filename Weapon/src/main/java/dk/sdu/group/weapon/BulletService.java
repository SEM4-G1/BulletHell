package dk.sdu.group.weapon;

import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;

public interface BulletService {
    void createBullet(Entity entity, EntityManager entityManager);
}