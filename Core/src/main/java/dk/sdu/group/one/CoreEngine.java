package dk.sdu.group.one;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.player.Player;
import dk.sdu.group.weapon.weapontypes.Pistol;

import java.util.ArrayList;
import java.util.List;


public class CoreEngine extends ApplicationAdapter {
    SpriteBatch batch;
    EntityManager entityManager;

    TextureCache textureCache;

    @Override
    public void create() {
        this.entityManager = new EntityManager();
        this.textureCache = new TextureCache();
        Player player = new Player("player.png", 5, 5);
        entityManager.addEntity(player);
        batch = new SpriteBatch();
//        img = new Texture(Gdx.files.internal("assets/test.jpg"));
        entityManager.addEntity(new Pistol(EntityType.Weapon,0, 0));
    }


    @Override
    public void render() {
        update();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT |  GL20.GL_DEPTH_BUFFER_BIT );
        batch.begin();

        for (Entity entity : entityManager.getEntityList()) {
            batch.draw(
                    textureCache.loadTexture(entity.getTexturePath()),
                    entity.getX(),
                    entity.getY());
        }
        batch.end();
    }

    public void update() {
        List<Entity> entities = List.copyOf(entityManager.getEntityList());
        entities.forEach(
                entity -> entity.process(entityManager, Gdx.graphics.getDeltaTime())
        );
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