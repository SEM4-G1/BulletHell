package dk.sdu.group.one.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class Vector2Tests {
    @Test
    void normalizationTest() {
        Vector2 vec = new Vector2(0, -1);
        vec = vec.normalize();

        assertEquals(-1, vec.getY());
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
