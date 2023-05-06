package dk.sdu.group.one.enemy.enemytypes;

import dk.sdu.group.one.ai_astar.Node;
import dk.sdu.group.one.ai_astar.helpers.Mappers;
import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.map.Coordinate;
import dk.sdu.group.one.map.MapService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class Melee extends Entity {
    private static String spritePath ="wormEnemy.png";

    private static int speed = 1;

    MapService mapService;

    Coordinate currentCoordinate;

    float cellWidth = 1980.0f/mapService.getWidth();
    float cellHeight = 1080.0f/mapService.getHeight();

    public Melee() {
        super(EntityType.ENEMY,spritePath, 0, 0, 100);
    }
    public Melee(float x, float y) {
        super(EntityType.ENEMY,spritePath, x, y, 100);
    }



    @Override
    public void process(EntityManager entityManager, double dt){
        Coordinate playerCoordinate;
        for(Entity entity : entityManager.getEntityList()){
            if (entity.getType() == EntityType.PLAYER){
                playerCoordinate = new Coordinate((int)entity.getX()/cellWidth, (int)entity.getY()/cellHeight)
            }
        }
        Node.aStar(Mappers.GenerateNodes(mapService));
        this.setX((float) (this.getX() + speed*dt));
        this.setY((float) (this.getY() + speed*dt));
    }

    @Override
    public void start(MapService mapService, EntityManager entityList) {
        this.mapService = mapService;

        for (int i = 0; i < 1; i++) {
             Coordinate melee_coordinate = new Coordinate((int)(Math.random()*mapService.getWidth()),(int)(Math.random()*mapService.getHeight()));
             while (!isUnique(entityList, cellWidth, cellHeight, melee_coordinate)){
                melee_coordinate = new Coordinate((int)(Math.random()*mapService.getWidth()),(int)(Math.random()*mapService.getHeight()));
             }
             float x = melee_coordinate.getX() * cellWidth;
             float y = melee_coordinate.getY() * cellHeight;
             this.currentCoordinate = melee_coordinate;
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
