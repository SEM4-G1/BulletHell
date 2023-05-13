package dk.sdu.group.weapon.weaponfacade;

import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.weapon.Weapon;
import dk.sdu.group.one.event.events.PickUpEvent;
import dk.sdu.group.one.event.EventProcessor;

public class WeaponPickUpPart implements EventProcessor<PickUpEvent> {
    private boolean isPickedUp;
    private final Weapon weapon;

    private Entity pickedUpBy;

    public WeaponPickUpPart(Weapon weapon) {
        this.isPickedUp = false;
        this.weapon = weapon;
    }

    @Override
    public void handleEvent(PickUpEvent event) {
        if (event.getTarget().equals(weapon)) {
            weapon.pickUp();
            pickedUpBy = event.getIssuer();
            this.isPickedUp = true;
        }
        else if(event.getIssuer().getType() == EntityType.Weapon){
            weapon.drop();
            this.isPickedUp = false;
            weapon.bulletService.unequip();
        }
    }
    public boolean isPickedUp() {
        return isPickedUp;
    }

    public Entity getPickedUpBy() {
        return pickedUpBy;
    }
}
