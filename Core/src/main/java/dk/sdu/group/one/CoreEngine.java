package dk.sdu.group.one;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.group.one.player.Player;
import dk.sdu.group.one.player.data.Entity;
import dk.sdu.group.one.player.data.EntityManager;


public class CoreEngine extends ApplicationAdapter {
    SpriteBatch batch;
    EntityManager entityManager;

    @Override
    public void create() {
        this.entityManager = new EntityManager();
        batch = new SpriteBatch();
        entityManager.addEntity(new Player("player.png", 100, 100));
//        img = new Texture(Gdx.files.internal("assets/test.jpg"));
    }


    @Override
    public void render() {
        update();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        batch.begin();

        for (Entity entity : entityManager.getEntityList()) {
            System.out.println(entity.getTexturePath());
            batch.draw(
                    new Texture(Gdx.files.internal("assets/" + entity.getTexturePath())),
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