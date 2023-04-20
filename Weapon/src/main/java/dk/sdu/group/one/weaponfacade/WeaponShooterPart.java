package dk.sdu.group.one.weaponfacade;

import dk.sdu.group.one.event.EventProcessor;
import dk.sdu.group.one.event.events.ShootEvent;

public class WeaponShooterPart implements EventProcessor<ShootEvent> {
    @Override
    public void handleEvent(ShootEvent event) {
        System.out.println("shooter Part shooting!");
        System.out.println(event.message);
    }
}