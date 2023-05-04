package dk.sdu.group.one.data;

public class Vector2 {
    private float x, y;

    public static Vector2 left = new Vector2(-1, 0);
    public static Vector2 right = new Vector2(1, 0);
    public static Vector2 up = new Vector2(0, 1);
    public static Vector2 down = new Vector2(0, -1);

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the magnitude of the Vector2 object.
     * @return the vector magnitude
     */
    public float getMagnitude() {
        return (float) Math.sqrt(x*x + y*y);
    }

    /**
     * Normalizes the Vector2
     * @return the normalized vector
     */
    public Vector2 normalize() {
        this.x = x/getMagnitude();
        this.y = y/getMagnitude();
        return this;
    }

    /**
     *
     * @return x value of vector object.
     */
    public float getX() {
        return this.x;
    }

    /**
     *
     * @return y value of vector object.
     */
    public float getY() {
        return this.y;
    }

    /**
     * Sets the x and y value of the vector
     * @param x
     * @param y
     * @return
     */
    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
