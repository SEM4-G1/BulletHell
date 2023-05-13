package dk.sdu.group.one.enemy.enemytypes;

import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.enemy.AI.AIservice;
import dk.sdu.group.one.enemy.AI.Path;
import dk.sdu.group.one.enemy.Cooldown;
import dk.sdu.group.one.event.EventProcessor;
import dk.sdu.group.one.event.events.CollisionEvent;
import dk.sdu.group.one.map.Coordinate;
import dk.sdu.group.one.map.MapService;

import java.util.*;

public class Melee extends Entity implements EventProcessor<CollisionEvent> {
    private static String spritePath ="monster_bies.png";

    private List<Path> path = new ArrayList<>();

    private Coordinate nextPoint;

    private float distanceToNextPoint;

    private static float speed = 0.5f;

    MapService mapService;

    Coordinate currentGridPos;
    Coordinate playerGridPos;

    Cooldown attackCooldown;

    Cooldown aiUpdateCooldown;

    float cellWidth;
    float cellHeight;

    AIservice aiService;

    public Melee() {
        super(EntityType.ENEMY,spritePath, 0, 0, 100);
    }
    public Melee(float x, float y) {
        super(EntityType.ENEMY,spritePath, x, y, 100);
    }



    @Override
    public void process(EntityManager entityManager, double dt){
        for(Entity entity : entityManager.getEntityList()){
            if (entity.getType() == EntityType.PLAYER){
                playerGridPos = new Coordinate((int) entity.getX()/ (int) this.cellWidth, (int)entity.getY()/(int) this.cellHeight);
                break;
            }
        }
        if(path.isEmpty() || aiUpdateCooldown.isReady()){
            updateCoordinate();
            this.path = aiService.getPath(this.currentGridPos, this.playerGridPos);
            System.out.println("---------------------------new path---------------------------");
            for(Path path:aiService.getPath(this.currentGridPos, this.playerGridPos)){
                System.out.println(path);
            }
            aiUpdateCooldown.reset();
        }
        aiUpdateCooldown.update(dt);
        attackCooldown.update(dt);
        if(!path.isEmpty()){
            processPath(dt);
        }
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
             this.currentGridPos = melee_coordinate;
             this.aiService = ServiceLoader.load(AIservice.class).findFirst().get();
             this.aiService.changeMap(mapService);
             this.addCooldowns();
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

    private void processPath(double dt){
        if (nextPoint == null){
            nextPoint = path.get(0).getCoordinate();
            distanceToNextPoint = (float) Math.sqrt(Math.pow(nextPoint.getX()*cellWidth - getX(), 2) + Math.pow(nextPoint.getY()*cellHeight - getY(), 2));
        }
        float distanceToMove = (float) (speed * dt);
        if(distanceToNextPoint < distanceToMove) {
            setX(nextPoint.getX()*cellWidth);
            setY(nextPoint.getY()*cellHeight);
            path.remove(0);
            if(!path.isEmpty()){
                nextPoint = path.get(0).getCoordinate();
                distanceToNextPoint = (float) Math.sqrt(Math.pow(nextPoint.getX()*cellWidth - getX(), 2) + Math.pow(nextPoint.getY()*cellHeight - getY(), 2));
            }

        } else {
            float x = getX();
            float y = getY();
            float x2 = nextPoint.getX()*cellWidth;
            float y2 = nextPoint.getY()*cellHeight;
            float dx = x2 - x;
            float dy = y2 - y;
            float distance = (float) Math.sqrt(dx * dx + dy * dy);
            float xMove = (dx / distance) * distanceToMove;
            float yMove = (dy / distance) * distanceToMove;
            setX(x + xMove);
            setY(y + yMove);
            distanceToNextPoint -= distanceToMove;
        }
    }
    private void updateCoordinate(){
        this.currentGridPos = new Coordinate((int) (getX()/cellWidth), (int) (getY()/cellHeight));
    }

    @Override
    public void handleEvent(CollisionEvent event) {
        if (collidedWithPlayer(event.getE1(), event.getE2())){
            attack();
        }
    }
    private boolean collidedWithPlayer(Entity e1, Entity e2){
        return (e1.equals(this) && e2.getType() == EntityType.PLAYER) || (e2.equals(this) && e1.getType() == EntityType.PLAYER);
    }
    private void attack(){
        if (attackCooldown.isReady()){
            attackCooldown.reset();
            System.out.println("Melee attacked");
        }
    }

    private void addCooldowns(){
        this.aiUpdateCooldown = new Cooldown(2);
        this.attackCooldown = new Cooldown(5);
    }
}