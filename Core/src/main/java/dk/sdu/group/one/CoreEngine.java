package dk.sdu.group.one;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.map.MapService;
import dk.sdu.group.one.mapmodule.Map;
import dk.sdu.group.one.player.Player;


public class CoreEngine extends ApplicationAdapter {
    SpriteBatch batch;
    EntityManager entityManager;
    Texture map;
    TextureCache textureCache;

    @Override
    public void create() {
        this.entityManager = new EntityManager();
        this.textureCache = new TextureCache();
        Player player = new Player("player.png", 5, 5);
        entityManager.addEntity(player);
        batch = new SpriteBatch();
        map = new Texture("assets/"+new Map().texture);
        //TODO this is a wonky way of loading map, so it should be changed to be more clean
//        img = new Texture(Gdx.files.internal("assets/test.jpg"));
    }


    @Override
    public void render() {
        update();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        batch.begin();
        batch.draw(map, 0,0);
        for (Entity entity : entityManager.getEntityList()) {
            System.out.println(entity.getTexturePath());
            batch.draw(
                    textureCache.loadTexture(entity.getTexturePath()),
                    entity.getX(),
                    entity.getY());
        }
        batch.end();
    }

    public void update() {

    }

    @Override
    public void dispose() {
        batch.dispose();
//        img.dispose();
    }
    public int multiply(int a, int b){
        return a*b;
    }
}