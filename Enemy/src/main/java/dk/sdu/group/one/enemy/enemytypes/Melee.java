package dk.sdu.group.one.enemy.enemytypes;

import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.map.Coordinate;
import dk.sdu.group.one.map.MapService;

public class Melee extends Entity {
    private static String spritePath ="player.png";

    public Melee() {
        super(EntityType.ENEMY,spritePath, 0, 0, 100);
    }

    @Override
    public void process(EntityManager entityManager, double dt){
    }

    @Override
    public void start(MapService mapService, EntityManager entityList) {

        double cellWidth = 1920/mapService.getWidth();
        double cellHeight = 1080/mapService.getHeight();

        
        for (int i = 0; i < 30; i++) {

            Melee melee = new Melee();
            Coordinate coordinate = new Coordinate((int)(Math.random()*mapService.getWidth()),(int)(Math.random()*mapService.getHeight()));
            while (mapService.getObstaclePositions().contains(coordinate)){
                System.out.println("------------------");
                System.out.println(coordinate);
                System.out.println(melee.hashCode());
                coordinate.setX((int)Math.floor((Math.random()*mapService.getWidth())));
                coordinate.setY((int)Math.floor((Math.random()*mapService.getHeight())));
            }


            melee.setX((float) (coordinate.getX() * cellWidth));
            melee.setY((float) (coordinate.getY() * cellHeight));
            entityList.addEntity(melee);
        }
    }
}
