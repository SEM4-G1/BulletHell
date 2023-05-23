package dk.sdu.group.one.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class Vector2Tests {
    @Test
    void normalizationTest() {
        Vector2 vec = new Vector2(15, 15);
        double originalAngle = Math.atan(vec.getY()/vec.getX());
        vec = vec.normalize();
        double length = (Math.sqrt(vec.getX() * vec.getX()) + (vec.getY() * vec.getY()));
        double newAngle = Math.atan(vec.getY()/vec.getX());
        assertTrue(1.00011 > length || length > 0.999);
        assertTrue(originalAngle == newAngle);
    }

    @Test
    void getMagnitudeTest() {
        Vector2 vec = new Vector2(2, 2);
        assertEquals(2.8284270763397217, vec.getMagnitude());

        vec = new Vector2(1, 0);
        assertEquals(1, vec.getMagnitude());

        vec = new Vector2(-4, 0);
        assertEquals(4, vec.getMagnitude());

        vec = new Vector2(-2, 2);
        assertEquals(2.8284270763397217, vec.getMagnitude());

        vec = new Vector2(-2, -2);
        assertEquals(2.8284270763397217, vec.getMagnitude());
    }
}
