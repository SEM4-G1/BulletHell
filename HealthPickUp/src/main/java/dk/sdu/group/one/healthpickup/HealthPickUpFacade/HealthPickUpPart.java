package dk.sdu.group.one.healthpickup.HealthPickUpFacade;

import dk.sdu.group.one.healthpickup.HealthPickUp;
import dk.sdu.group.one.event.EventProcessor;
import dk.sdu.group.one.event.events.PickUpEvent;

public class HealthPickUpPart implements EventProcessor<PickUpEvent> {
    private boolean isPickedUp;
    private final HealthPickUp healthPickUp;

    public HealthPickUpPart(HealthPickUp healthPickUp){
        this.isPickedUp = false;
        this.healthPickUp = healthPickUp;
    }

    @Override
    public void handleEvent(PickUpEvent event){
        if (!isPickedUp){
            System.out.println("HealthPickUp Detected by PickUpPart");
            healthPickUp.pickup(event.getIssuer());
            this.isPickedUp = true;
        }
    }
    public boolean isPickedUp(){
            return isPickedUp;
    }
}
