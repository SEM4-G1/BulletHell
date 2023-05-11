package dk.sdu.group.one.HealthPickUp.HealthPickUpFacade;

import dk.sdu.group.one.HealthPickUp.HealthPickUp;
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
        System.out.println(event.code);
        if (!isPickedUp){
            System.out.println("HealthPickUp Detected by PickUpPart");
            healthPickUp.pickup();
            this.isPickedUp = true;
        }
    }
}
