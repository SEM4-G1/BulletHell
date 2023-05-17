package dk.sdu.group.one.enemy.enemytypes;

import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.enemy.AI.AIservice;
import dk.sdu.group.one.enemy.AI.Path;
import dk.sdu.group.one.enemy.Cooldown;
import dk.sdu.group.one.event.EventBroker;
import dk.sdu.group.one.event.EventProcessor;
import dk.sdu.group.one.event.events.CollisionEvent;
import dk.sdu.group.one.event.events.EventType;
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
    boolean isAttacking = false;

    public Melee() {
        super(EntityType.ENEMY,spritePath, 0, 0, 100, 20, 20);
    }
    public Melee(float x, float y) {
        super(EntityType.ENEMY,spritePath, x, y, 20, 20, 100);
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
            aiUpdateCooldown.reset();
        }
        aiUpdateCooldown.update(dt);
        attackCooldown.update(dt);
        if(!path.isEmpty()){
            processPath(dt);
        }
        if(getCurrentHealth() <= 0){
            entityManager.removeEntity(this);
        }
        if (isAttacking){
            List<Entity> explosions = new ArrayList<>();
            explosions.add(new MeleeExplosion(EntityType.ATTACK, super.getX()+cellWidth ,super.getY()));
            explosions.add(new MeleeExplosion(EntityType.ATTACK, super.getX()-cellWidth ,super.getY()));
            explosions.add(new MeleeExplosion(EntityType.ATTACK, super.getX() ,super.getY()+cellHeight));
            explosions.add(new MeleeExplosion(EntityType.ATTACK, super.getX() ,super.getY()-cellHeight));
            explosions.add(new MeleeExplosion(EntityType.ATTACK, super.getX()+cellWidth ,super.getY()+cellHeight));
            explosions.add(new MeleeExplosion(EntityType.ATTACK, super.getX()-cellWidth ,super.getY()-cellHeight));
            explosions.add(new MeleeExplosion(EntityType.ATTACK, super.getX()+cellWidth ,super.getY()-cellHeight));
            explosions.add(new MeleeExplosion(EntityType.ATTACK, super.getX()-cellWidth ,super.getY()+cellHeight));
            explosions.forEach(entityManager::addEntity);
            isAttacking = false;
        }
    }

    @Override
    public void start(MapService mapService, EntityManager entityList) {
        for (int i = 0; i < 1; i++){
            Melee melee = new Melee();
            melee.mapService = mapService;
            melee.cellWidth = 480.0f/mapService.getWidth();
            melee.cellHeight = 480.0f/mapService.getHeight();

            Coordinate melee_coordinate = new Coordinate((int)(Math.random()*mapService.getWidth()),(int)(Math.random()*mapService.getHeight()));
            //TODO GETS STUCK IN INFINITE LOOP when more than 1 enemy is spawned
             while (!isUnique(entityList, cellWidth, cellHeight, melee_coordinate)){
                melee_coordinate = new Coordinate((int)(Math.random()*mapService.getWidth()),(int)(Math.random()*mapService.getHeight()));
             }

             melee.setX(melee_coordinate.getX() * cellWidth);
             melee.setY(melee_coordinate.getY() * cellHeight);
             melee.currentGridPos = melee_coordinate;
             melee.aiService = ServiceLoader.load(AIservice.class).findFirst().get();
             melee.aiService.changeMap(mapService);
             melee.addCooldowns();
             entityList.addEntity(melee);
             EventBroker.getInstance().subscribe(EventType.Collision, melee);
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
            if(!path.isEmpty()){
                nextPoint = path.get(0).getCoordinate();
                distanceToNextPoint = (float) Math.sqrt(Math.pow(nextPoint.getX()*cellWidth - getX(), 2) + Math.pow(nextPoint.getY()*cellHeight - getY(), 2));
            }
            path.remove(0);
        } else {
            float x1 = getX();
            float y1 = getY();
            float x2 = nextPoint.getX()*cellWidth;
            float y2 = nextPoint.getY()*cellHeight;
            float dx = x2 - x1;
            float dy = y2 - y1;
            float distance = (float) Math.sqrt(dx * dx + dy * dy);
            float xMoveDistance = dx  * (distanceToMove/ distance);
            float yMoveDisance = dy  * (distanceToMove/ distance);
            setX(x1 + xMoveDistance);
            setY(y1 + yMoveDisance);
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
            this.isAttacking = true;
            attackCooldown.reset();
        }
    }

    private void addCooldowns(){
        this.aiUpdateCooldown = new Cooldown(10);
        this.attackCooldown = new Cooldown(30);
    }
}