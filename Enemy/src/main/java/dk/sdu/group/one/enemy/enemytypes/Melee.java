package dk.sdu.group.one.enemy.enemytypes;

import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.map.Coordinate;
import dk.sdu.group.one.map.MapService;

public class Melee extends Entity {
    private static String spritePath ="enemy.png";

    public Melee() {
        super(EntityType.ENEMY,spritePath, 0, 0, 100);
    }

    @Override
    public void process(EntityManager entityManager, double dt){
    }

    @Override
    public void start(MapService mapService, EntityManager entityList) {
        double cellWidth = 1980/mapService.getWidth();
        double cellHeight = 1080/mapService.getHeight();
        for (int i = 0; i < 30; i++) {
            System.out.println(i);
            Melee melee = new Melee();
            Coordinate coordinate = new Coordinate((int)(Math.random()*mapService.getHeight()),(int)(Math.random()*mapService.getWidth()), true);
            while (mapService.getObstaclePositions().contains(coordinate)){
                coordinate = new Coordinate((int)(Math.random()*mapService.getHeight()),(int)(Math.random()*mapService.getWidth()), true);
                System.out.println("check");
            }
            melee.setX(coordinate.getX() * (int) cellWidth);
            melee.setY(coordinate.getY() * (int) cellHeight);
            entityList.addEntity(melee);
        }
        }
    }
