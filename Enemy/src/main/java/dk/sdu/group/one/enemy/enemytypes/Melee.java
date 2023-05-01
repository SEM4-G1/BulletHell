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
    public Melee(float x, float y) {
        super(EntityType.ENEMY,spritePath, x, y, 100);
    }

    @Override
    public void process(EntityManager entityManager, double dt){
    }

    @Override
    public void start(MapService mapService, EntityManager entityList) {
        float cellWidth = 1980.0f/mapService.getWidth();
        float cellHeight = 1080.0f/mapService.getHeight();

        for (int i = 0; i < 30; i++) {
             Coordinate melee_coordinate = new Coordinate((int)(Math.random()*mapService.getWidth()),(int)(Math.random()*mapService.getHeight()));
             while (!isUnique(entityList, cellWidth, cellHeight, melee_coordinate)){
                melee_coordinate = new Coordinate((int)(Math.random()*mapService.getWidth()),(int)(Math.random()*mapService.getHeight()));
             }
             float x = melee_coordinate.getX() * cellWidth;
             float y = melee_coordinate.getY() * cellHeight;
             entityList.addEntity(new Melee(x, y));
        }
    }

    private boolean isUnique(EntityManager entityList, float cellWidth, float cellHeight, Coordinate finalMelee_coordinate) {
        return entityList.getEntityList().stream()
                .filter(entity -> entity.getX() == finalMelee_coordinate.getX() * cellWidth
                        &&
                        entity.getY() == finalMelee_coordinate.getY() * cellHeight)
                .findFirst().isEmpty();
    }
}
