package dk.sdu.group.one.data;

public class Vector2 {
    private float x, y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getMagnitude() {
        return (float) Math.sqrt(x*x + y*y);
    }

    public Vector2 normalize() {
        return new Vector2(x/getMagnitude(), y/getMagnitude());
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;

    }
}
