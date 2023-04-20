package dk.sdu.group.one.data;

import com.badlogic.gdx.Gdx;
import dk.sdu.group.one.map.MapService;
import com.badlogic.gdx.graphics.Texture;

public abstract class Entity {
    Texture sprite;
    int x, y;

    EntityType type;

    public Entity(EntityType entityType, String spritePath, int x, int y) {
        this.type = entityType;
        this.sprite = new Texture(Gdx.files.internal("assets/" + spritePath));
        this.x = x;
        this.y = y;
    }

    public EntityType getType() {
        return this.type;
    }

    public EntityType getType() {
        return this.type;
    }

    int getX() {
        return this.x;
    }

    int getY() {
        return this.y;
    }

    public abstract void process();

    <<<<<<<HEAD

    public abstract void start(MapService mapService, EntityList entityList);=======

    public abstract void start(MapService mapService, EntityManager entityList);>>>>>>>6d f10ca3a78e1f66c7e7c6771eaeb7d3056d298f

}