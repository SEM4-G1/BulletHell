package dk.sdu.group.weapon.weaponfacade;

import dk.sdu.group.one.event.EventProcessor;
import dk.sdu.group.one.event.events.ShootEvent;
import dk.sdu.group.weapon.Weapon;

public class WeaponShooterPart implements EventProcessor<ShootEvent> {
    Weapon weapon;
    public WeaponShooterPart(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public void handleEvent(ShootEvent event) {
        weapon.bulletService.createBullet(weapon, event.entityManager);
        System.out.println("shooter Part shooting!");
    }
}