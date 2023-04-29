package dk.sdu.group.one.rock;
import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
import dk.sdu.group.one.map.Coordinate;
import dk.sdu.group.one.map.MapService;

public class Rock extends Entity{
    private static final String spritePath = "rock.png";
    public Rock() {
        super(EntityType.OBSTACLE, spritePath, 1, 1);
    }

    public Rock(EntityType type, int x, int y) {
        super(type, spritePath, x, y);
    }
    @Override
    public void process(EntityManager entityManager, double dt) {

    }

    @Override
    public void start(MapService mapService, EntityManager entityList) {
        for (Coordinate coordinate : mapService.getObstaclePositions()) {
            double cellWidth = 1980/mapService.getWidth();
            double cellHeight = 1080/mapService.getHeight();
            int x = coordinate.getX() * (int) cellWidth;
            int y = coordinate.getY() * (int) cellHeight;
            entityList.addEntity(new Rock(EntityType.OBSTACLE, x, y));
        }
    }
}
