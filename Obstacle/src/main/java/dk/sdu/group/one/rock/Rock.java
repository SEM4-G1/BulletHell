package dk.sdu.group.one.rock;
import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.map.Coordinate;
import dk.sdu.group.one.map.MapService;

public class Rock extends Entity{
    private static final String spritePath = "rock.png";
    public Rock() {
        super(EntityType.OBSTACLE, spritePath, 0.0f, 0.0f);
    }

    public Rock(EntityType type, float x, float y) {
        super(type, spritePath, x, y);
    }
    @Override
    public void process(EntityManager entityManager, double dt) {

    }

    @Override
    public void start(MapService mapService, EntityManager entityList) {
        float cellWidth = (1980.0f/mapService.getWidth());
        float cellHeight = (1080.0f/mapService.getHeight());

        for (Coordinate coordinate : mapService.getObstaclePositions()) {
            float x = coordinate.getX() * cellWidth;
            float y = coordinate.getY() * cellHeight;
            entityList.addEntity(new Rock(EntityType.OBSTACLE, x, y));
        }
    }
}