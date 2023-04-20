package dk.sdu.group.one.player.data;

import dk.sdu.group.one.player.map.MapService;

public abstract class Entity {
    String texturePath;
    int x, y;
    float radians;

    EntityType type;

    public Entity(EntityType entityType, String spritePath, int x, int y) {
        this.type = entityType;
        this.texturePath = spritePath;
        this.x = x;
        this.y = y;
    }

    public EntityType getType() {
        return this.type;
    }

    public int getX() {
        return this.x;
    }

    public void setRadians(float rads) {
        this.radians = rads;
    }

    public float getRadians() {
        return this.radians;
    }

    public int getY() {
        return this.y;
    }

    public String getTexturePath() {
        return this.texturePath;
    }

    public abstract void process(EntityManager entityManager);

    public abstract void start(MapService mapService, EntityManager entityList);

}