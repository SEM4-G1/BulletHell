package dk.sdu.group.one.enemy.enemytypes;

import dk.sdu.group.one.ai_astar.Node;
import dk.sdu.group.one.ai_astar.helpers.Mappers;
import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.enemy.AI.AIservice;
import dk.sdu.group.one.enemy.AI.Path;
import dk.sdu.group.one.map.Coordinate;
import dk.sdu.group.one.map.MapService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class Melee extends Entity implements AIservice {
    private static String spritePath ="monster_bies.png";

    private static int speed = 1;

    MapService mapService;

    Coordinate currentCoordinate;
    Coordinate playerCoordinate;

    float cellWidth;
    float cellHeight;

    public Melee() {
        super(EntityType.ENEMY,spritePath, 0, 0, 100);
    }
    public Melee(float x, float y) {
        super(EntityType.ENEMY,spritePath, x, y, 100);
    }


    boolean testbool = true;
    @Override
    public void process(EntityManager entityManager, double dt){
        for(Entity entity : entityManager.getEntityList()){
            if (entity.getType() == EntityType.PLAYER){
                playerCoordinate = new Coordinate((int) entity.getX()/ (int) this.cellWidth, (int)entity.getY()/(int) this.cellHeight);
                break;
            }
        }
        if(testbool){
            for(Coordinate coordinate:Node.aStar(currentCoordinate, playerCoordinate, Mappers.GenerateNodes(mapService))){
                System.out.println(coordinate);
            }
            testbool = false;
        }
        this.setX((float) (this.getX() + speed*dt));
        this.setY((float) (this.getY() + speed*dt));
    }

    @Override
    public void start(MapService mapService, EntityManager entityList) {
        this.mapService = mapService;
        this.cellWidth = 480.0f/mapService.getWidth();
        this.cellHeight = 480.0f/mapService.getHeight();

        for (int i = 0; i < 1; i++) {
             Coordinate melee_coordinate = new Coordinate((int)(Math.random()*mapService.getWidth()),(int)(Math.random()*mapService.getHeight()));
             while (!isUnique(entityList, cellWidth, cellHeight, melee_coordinate)){
                melee_coordinate = new Coordinate((int)(Math.random()*mapService.getWidth()),(int)(Math.random()*mapService.getHeight()));
             }
             this.setX(melee_coordinate.getX() * cellWidth);
             this.setY(melee_coordinate.getY() * cellHeight);
             this.currentCoordinate = melee_coordinate;
             entityList.addEntity(this);
        }
    }

    private boolean isUnique(EntityManager entityList, float cellWidth, float cellHeight, Coordinate finalMelee_coordinate) {
        return entityList.getEntityList().stream()
                .filter(entity -> entity.getX() == finalMelee_coordinate.getX() * cellWidth
                        &&
                        entity.getY() == finalMelee_coordinate.getY() * cellHeight)
                .findFirst().isEmpty();
    }

    @Override
    public List<Path> getPath() {
        return null;
    }
}
