package dk.sdu.group.one.enemy.AI;

import dk.sdu.group.one.data.Vector2;
import dk.sdu.group.one.map.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class PathNaturalizer {
    public static List<Coordinate> createSpline(List<Coordinate> points, int numPoints, float cellWidth, float cellHeight) {
        List<Coordinate> pixelPoints = new ArrayList<>();

        for (int i = 1; i < points.size() - 2; i++) {
            Coordinate p0 = points.get(i - 1);
            Coordinate p1 = points.get(i);
            Coordinate p2 = points.get(i + 1);
            Coordinate p3 = points.get(i + 2);

            for (int j = 0; j < numPoints; j++) {
                float t = (float) j / (float) numPoints;

                float x = 0.5f * ((2.0f * p1.getX()) +
                        (-p0.getX() + p2.getX()) * t +
                        (2.0f * p0.getX() - 5.0f * p1.getX() + 4.0f * p2.getX() - p3.getX()) * t * t +
                        (-p0.getX() + 3.0f * p1.getX() - 3.0f * p2.getX() + p3.getX()) * t * t * t);

                float y = 0.5f * ((2.0f * p1.getY()) +
                        (-p0.getY() + p2.getY()) * t +
                        (2.0f * p0.getY() - 5.0f * p1.getY() + 4.0f * p2.getY() - p3.getY()) * t * t +
                        (-p0.getY() + 3.0f * p1.getY() - 3.0f * p2.getY() + p3.getY()) * t * t * t);

                pixelPoints.add(new Coordinate((int)(x * cellWidth), (int)(y * cellHeight)));
            }
        }

        return pixelPoints;
    }
}
