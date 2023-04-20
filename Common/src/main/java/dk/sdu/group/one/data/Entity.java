package dk.sdu.group.one.data;

import com.badlogic.gdx.Gdx;
import dk.sdu.group.one.map.MapService;
import com.badlogic.gdx.graphics.Texture;

public abstract class Entity {
    Texture texture;
    int x, y;
    float radians;

    EntityType type;

    public Entity(EntityType entityType, String spritePath, int x, int y) {
        this.type = entityType;
        this.texture = new Texture(Gdx.files.internal("assets/" + spritePath));
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

    public Texture getTexture() {
        return this.texture;
    }

    public abstract void process(EntityManager entityManager);

    public abstract void start(MapService mapService, EntityManager entityList);>>>>>>>6d f10ca3a78e1f66c7e7c6771eaeb7d3056d298f

}