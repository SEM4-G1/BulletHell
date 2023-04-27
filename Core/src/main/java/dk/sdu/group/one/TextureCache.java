package dk.sdu.group.one;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class TextureCache {
    HashMap<String, Texture> map = new HashMap<>();

    public Texture loadTexture(String path) {
        if (!isLoaded(path)) map.put(path, new Texture(Gdx.files.internal("assets/" + path)));
        return map.get(path);
    }

    private boolean isLoaded(String path) {
        return map.containsKey(path);
    }
}
