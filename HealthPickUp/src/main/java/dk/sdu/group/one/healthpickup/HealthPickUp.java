package dk.sdu.group.one.healthpickup;

import dk.sdu.group.one.healthpickup.HealthPickUpFacade.HealthPickUpPart;
import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.event.EventBroker;
import dk.sdu.group.one.event.events.EventType;
import dk.sdu.group.one.map.Coordinate;
import dk.sdu.group.one.map.MapService;

public class HealthPickUp extends Entity {
     public HealthPickUpPart healthPickUpPart = new HealthPickUpPart(this);

     private static final String spritePath = "health_pickup.png";
     private int healthRestored;

    public HealthPickUp(int x, int y){
        super(EntityType.Health, spritePath, x, y, 10);
    }
    public HealthPickUp(){
        super(EntityType.Health, spritePath, 0, 0, 10);
    }


    @Override
    public void process(EntityManager entityManager, double dt){
        if (this.healthPickUpPart.isPickedUp()){
            entityManager.removeEntity(this);
            EventBroker.getInstance().unsubscribe(EventType.PickUpEvent, healthPickUpPart);
        }
    }

    @Override
    public void start(MapService mapService, EntityManager entityList){
        int cellWidth = (int) (480.0f/mapService.getWidth());
        int cellHeight = (int) (480.0f/mapService.getHeight());
        for (int i = 0; i < 1; i++){
            HealthPickUp healthPickUp = new HealthPickUp( 20, 20);
            Coordinate melee_coordinate = new Coordinate((int)(Math.random()*mapService.getWidth()),(int)(Math.random()*mapService.getHeight()));
            while (!isUnique(entityList, cellWidth, cellHeight, melee_coordinate)){
                melee_coordinate = new Coordinate((int)(Math.random()*mapService.getWidth()),(int)(Math.random()*mapService.getHeight()));
            }

            healthPickUp.setX(melee_coordinate.getX() * cellWidth);
            healthPickUp.setY(melee_coordinate.getY() * cellHeight);
            entityList.addEntity(healthPickUp);
            EventBroker.getInstance().subscribe(EventType.PickUpEvent, healthPickUp.healthPickUpPart);
        }
    }

     public void pickup(Entity entity){
         System.out.println("health picked up");
         healthRestored = (int) (Math.random() * 5 + 100);
         entity.heal(healthRestored);
     }

    private boolean isUnique(EntityManager entityList, float cellWidth, float cellHeight, Coordinate final_coordinate) {
        return entityList.getEntityList().stream()
                .filter(entity -> entity.getX() == final_coordinate.getX() * cellWidth
                        &&
                        entity.getY() == final_coordinate.getY() * cellHeight)
                .findFirst().isEmpty();
    }
}
