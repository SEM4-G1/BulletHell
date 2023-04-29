package dk.sdu.group.one.enemy.enemytypes;

import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.map.Coordinate;
import dk.sdu.group.one.map.MapService;

public class Melee extends Entity {
    private static String spritePath ="enemy.png";

    public Melee() {
        super(EntityType.ENEMY,spritePath, 1.0f, 1.0f, 100);
    }

    @Override
    public void process(EntityManager entityManager, double dt){
    }

    @Override
    public void start(MapService mapService, EntityManager entityList) {
        Melee melee = new Melee();
        Coordinate coordinate = new Coordinate((int)(Math.random()*mapService.getHeight()),(int)(Math.random()*mapService.getWidth()) );
        while (mapService.getObstaclePositions().contains(coordinate))
        melee.setX(coordinate.getX());
        melee.setY(coordinate.getY());
        entityList.addEntity(melee);
    }
}