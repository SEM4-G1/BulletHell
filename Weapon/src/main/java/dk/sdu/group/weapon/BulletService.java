package dk.sdu.group.weapon;

import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;

public interface BulletService {
    void equip(String equippedAsset, float cooldown, EntityManager entityManager, Entity equipper);
    void createBullet(Entity entity, EntityManager entityManager);
    void unequip();
}