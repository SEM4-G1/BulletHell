package dk.sdu.group.one.enemy.enemytypes;

import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.map.Coordinate;
import dk.sdu.group.one.map.MapService;

import java.util.List;
import java.util.Optional;

public class Melee extends Entity {
    private static String spritePath ="wormEnemy.png";

    public Melee() {
        super(EntityType.ENEMY,spritePath, 0, 0, 100);
    }

    @Override
    public void process(EntityManager entityManager, double dt){
    }

    @Override
    public void start(MapService mapService, EntityManager entityList) {

        float cellWidth = 1980.0f/mapService.getWidth();
        float cellHeight = 1080.0f/mapService.getHeight();
        System.out.println("Melee: Cell width: " + cellWidth + ", Cell height: " + cellHeight);
        for (int i = 0; i < 30; i++) {

            Melee melee = new Melee();

            Coordinate melee_coordinate = new Coordinate((int)(Math.random()*mapService.getWidth()),(int)(Math.random()*mapService.getHeight()));
             while (true){
                 Coordinate finalMelee_coordinate = melee_coordinate;
                  Optional<Entity> result = entityList.getEntityList().stream()
                          .filter(entity -> entity.getX() == finalMelee_coordinate.getX() * cellWidth
                                  &&
                                  entity.getY() == finalMelee_coordinate.getY() * cellHeight)
                          .findFirst();
                    if (result.isEmpty()){
                        break;
                    }
                melee_coordinate = new Coordinate((int)(Math.random()*mapService.getWidth()),(int)(Math.random()*mapService.getHeight()));
             }
            melee.setX(melee_coordinate.getX() * cellWidth);
            melee.setY(melee_coordinate.getY() * cellHeight);
            System.out.println("Melee added at: " + melee.getX() + ", " + melee.getY() + "(" + melee_coordinate.getX() + ", " + melee_coordinate.getY() + ")" + "hashcode:" +  melee.hashCode());
            entityList.addEntity(melee);
        }
    }
}
