package dk.sdu.group.one.data;

public class Vector2 {
    private double x, y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getMagnitude() {
        return Math.sqrt(x*x + y*y);
    }

    public Vector2 normalize() {
        return new Vector2(x/getMagnitude(), y/getMagnitude());
    }
}
