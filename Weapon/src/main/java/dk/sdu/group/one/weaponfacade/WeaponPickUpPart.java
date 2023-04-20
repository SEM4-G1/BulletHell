package dk.sdu.group.one.weaponfacade;

import dk.sdu.group.one.Weapon;
import dk.sdu.group.one.event.events.PickUpEvent;
import dk.sdu.group.one.event.EventProcessor;

public class WeaponPickUpPart implements EventProcessor<PickUpEvent> {
    private boolean isPickedUp;
    private final Weapon weapon;

    public WeaponPickUpPart(Weapon weapon) {
        this.isPickedUp = false;
        this.weapon = weapon;
    }

    @Override
    public void handleEvent(PickUpEvent event) {
        System.out.println(event.code);
        if (!isPickedUp){
            System.out.println("pick up detected by pickupPart!");
            weapon.pickUp();
            this.isPickedUp = true;
            return;
        }
        weapon.drop();
        this.isPickedUp = false;
    }
}
