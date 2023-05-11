package dk.sdu.group.one.data;

import java.util.ArrayList;
import java.util.List;
import java.util.EnumMap;
import java.util.Map;

public class EntityManager {
    List<Entity> entityList;
    Map<EntityType, Entity> entityMap;

    public EntityManager() {
        this.entityList = new ArrayList<>();
        entityMap = new EnumMap<EntityType, Entity>(EntityType.class);
    }

    public void addEntity(Entity entity) {
        this.entityList.add(entity);
        this.entityMap.put(entity.getType(), entity);
    }

    public void removeEntity(Entity entity){
        this.entityList.remove(entity);
        this.entityMap.remove(entity.getType(), entity);
    }

    public List<Entity> getEntityList() {
        return this.entityList;
    }

    public Map<EntityType, Entity> getEntityMap() {
        return this.entityMap;
    }



}
