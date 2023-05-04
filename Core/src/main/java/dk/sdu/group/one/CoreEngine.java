package dk.sdu.group.one;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.enemy.enemytypes.Melee;
import dk.sdu.group.one.map.Coordinate;
import dk.sdu.group.one.player.Player;

import dk.sdu.group.one.rock.Rock;
import dk.sdu.group.one.services.LevelService;

import javax.swing.*;
import java.util.List;
import java.util.ServiceLoader;


public class CoreEngine extends ApplicationAdapter {
    SpriteBatch batch;
    EntityManager entityManager;
    Texture mapTexture;

    Texture currentMap[][];
    LevelService mapProvider;
    TextureCache textureCache;
    private OrthographicCamera camera;
    public CoreEngine() {
        mapProvider = ServiceLoader.load(LevelService.class).findFirst().get();
    }

    @Override
    public void create() {
        this.currentMap = new Texture[30][30];
        this.textureCache = new TextureCache();
        Player player = new Player("player.png", 5, 5);
        //entityManager.addEntity(player)
        String[][] mapAsset = mapProvider.getCurrentLevel().getMapAsset();
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                this.currentMap[i][j] = textureCache.loadTexture(mapAsset[i][j]);
            }
        }
        setUpCamera();

        this.entityManager = new EntityManager();

        startEntities();
        batch = new SpriteBatch();
        //TODO this is a wonky way of loading map, so it should be changed to be more clean
//        img = new Texture(Gdx.files.internal("assets/test.jpg"));

    }


    @Override
    public void render() {
        update();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT |  GL20.GL_DEPTH_BUFFER_BIT );
        batch.begin();
       // batch.draw(mapTexture, 0,0);
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                batch.draw(currentMap[i][j], i*16, j*16);
            }
        }

        for (int i = entityManager.getEntityList().size()-1; i >=0 ; i--) {
            entityManager.getEntityList().get(i).process(entityManager,1);
            //System.out.println(entity.getTexturePath());
            batch.draw(
                    textureCache.loadTexture(entityManager.getEntityList().get(i).getTexturePath()),
                    entityManager.getEntityList().get(i).getX(),
                    entityManager.getEntityList().get(i).getY());
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

    private void setUpCamera(){
        int width = 480;
        int height = 480;
        this.camera = new OrthographicCamera(width, height);
        //this.camera.translate(width / 2.0f, height / 2.0f);
        camera.update();
    }

    private void startEntities(){
//        Entity entity = new Rock();
//        entity.start(mapProvider.getCurrentLevel(), entityManager);
        Entity player = new Player();
        player.start(mapProvider.getCurrentLevel(), entityManager);
        Entity enemy = new Melee();
        enemy.start(mapProvider.getCurrentLevel(), entityManager);
    }

    @Override
    public void resize(int width, int height) {
        this.camera.viewportWidth =  width;
        this.camera.viewportHeight = height;
        this.camera.update();
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
        System.out.println("Resize");
    }
}