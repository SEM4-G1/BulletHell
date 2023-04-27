package dk.sdu.group.one.rock;
import dk.sdu.group.one.data.Entity;
import dk.sdu.group.one.data.EntityManager;
import dk.sdu.group.one.data.EntityType;
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
        for (int[] position : mapService.getObstaclePositions()) {
            // THIS IS HARDCODED
            double cellWidth = 1980/30;
            double cellHeight = 1080/30;
            int x = position[0] * (int) cellWidth;
            int y = position[1] * (int) cellHeight;
            System.out.println("x: " + x + " y: " + y);
            entityList.addEntity(new Rock(EntityType.OBSTACLE, x, y));
        }
    }
}
