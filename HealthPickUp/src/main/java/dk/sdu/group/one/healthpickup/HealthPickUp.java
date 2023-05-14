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

    private boolean isPickedUp;

    public HealthPickUp(int x, int y){
        super(EntityType.Health, spritePath, x, y, 10);
    }
    public HealthPickUp(){
        super(EntityType.Health, spritePath, 0, 0, 10);
    }


    @Override
    public void process(EntityManager entityManager, double dt){
    }

    @Override
    public void start(MapService mapService, EntityManager entityList){
        HealthPickUp healthPickUp = new HealthPickUp(EntityType.Health, spritePath,0 ,0);
        int cellWidth = (int) (480.0f/mapService.getWidth());
        int cellHeight = (int) (480.0f/mapService.getHeight());
        for (int i = 0; i < 1; i++){
            HealthPickUp pistol = new HealthPickUp( 20, 20);
            Coordinate melee_coordinate = new Coordinate((int)(Math.random()*mapService.getWidth()),(int)(Math.random()*mapService.getHeight()));
            while (!isUnique(entityList, cellWidth, cellHeight, melee_coordinate)){
                melee_coordinate = new Coordinate((int)(Math.random()*mapService.getWidth()),(int)(Math.random()*mapService.getHeight()));
            }

            pistol.setX(melee_coordinate.getX() * cellWidth);
            pistol.setY(melee_coordinate.getY() * cellHeight);
            entityList.addEntity(pistol);
            EventBroker.getInstance().subscribe(EventType.PickUpEvent, healthPickUp.healthPickUpPart);
        }
    }

    public HealthPickUp(EntityType entityType, String spritePath, float x, float y){
         super(entityType, spritePath, x, y);
     }

     public void pickup(){
         System.out.println("health picked up");
         EventBroker.getInstance().subscribe(EventType.PickUpEvent, healthPickUpPart);
         healthRestored = (int) (Math.random() * 5 + 1);

     }

    private boolean isUnique(EntityManager entityList, float cellWidth, float cellHeight, Coordinate final_coordinate) {
        return entityList.getEntityList().stream()
                .filter(entity -> entity.getX() == final_coordinate.getX() * cellWidth
                        &&
                        entity.getY() == final_coordinate.getY() * cellHeight)
                .findFirst().isEmpty();
    }
}
