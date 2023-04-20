package dk.sdu.group.one.data;

import com.badlogic.gdx.Gdx;
import dk.sdu.group.one.map.MapService;
import com.badlogic.gdx.graphics.Texture;

public abstract class Entity {
    Texture sprite;
    int x, y;

    public Entity(String spritePath, int x, int y) {
        this.sprite = new Texture(Gdx.files.internal("assets/" + spritePath));
        this.x = x;
        this.y = y;
    }

    int getX() { return this.x; }

    int getY() { return this.y; }

    public abstract void process();

    public abstract void start(MapService mapService);

}