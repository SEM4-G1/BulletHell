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
import dk.sdu.group.one.services.PostProcessingService;

import java.util.List;
import java.util.ServiceLoader;


public class CoreEngine extends ApplicationAdapter {
    SpriteBatch batch;
    EntityManager entityManager;
    Texture mapTexture;
    PostProcessingService postProcessingService;
    Texture currentMap[][];
    LevelService mapProvider;
    TextureCache textureCache;
    private OrthographicCamera camera;
    public CoreEngine() {
        mapProvider = ServiceLoader.load(LevelService.class).findFirst().get();
        postProcessingService = ServiceLoader.load(PostProcessingService.class).findFirst().get();
    }

    @Override
    public void create() {
        this.textureCache = new TextureCache();
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
        batch.draw(currentMap, 0, 0);
        for (Entity entity : entityManager.getEntityList()) {
            entity.process(entityManager,1);
            //System.out.println(entity.getTexturePath());
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
        postProcessingService.postProcess(entityManager);

    }

    @Override
    public void dispose() {
        batch.dispose();
//        img.dispose();
    }

    private void setUpCamera(){
        int width = 1920;
        int height = 1080;
        this.camera = new OrthographicCamera(width, height);
        this.camera.translate(width / 2.0f, height / 2.0f);
        camera.update();
    }

    private void startEntities(){
//        Entity entity = new Rock();
//        entity.start(mapProvider.getCurrentLevel(), entityManager);
        Entity player = new Player();
        player.start(mapProvider.getCurrentLevel(), entityManager);
        Entity enemy = new Melee();
        enemy.start(mapProvider.getCurrentLevel(), entityManager);
        Entity entity1 = new Player("player.png" ,5,5);
        entity1.start(mapProvider.getCurrentLevel(), entityManager);
        Entity entity2 = new Player("player.png" ,5,5);
        entity2.start(mapProvider.getCurrentLevel(), entityManager);
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