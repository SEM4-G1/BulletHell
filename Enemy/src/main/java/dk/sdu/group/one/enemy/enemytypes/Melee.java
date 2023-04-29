package dk.sdu.group.one.enemy.enemytypes;

import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.enemy.Enemy;
import dk.sdu.group.one.map.MapService;

public class Melee extends Enemy {
    MapService mapService;
    public Melee(String spritePath, int x, int y, MapService mapService) {
        super(spritePath, x, y, mapService);
        this.mapService = mapService;
    }

    @Override
    public void process(EntityManager entityManager, double dt){
    }

    @Override
    public void start(MapService mapService, EntityManager entityList) {
        System.out.println("start");
    }
}
