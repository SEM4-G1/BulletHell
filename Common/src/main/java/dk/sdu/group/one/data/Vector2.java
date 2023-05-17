package dk.sdu.group.one.data;

public class Vector2{
    private float x, y;

    // Inspired by Unity
    public static Vector2 zero = new Vector2(0, 0);
    public static Vector2 left = new Vector2(-1, 0);
    public static Vector2 right = new Vector2(1, 0);
    public static Vector2 up = new Vector2(0, 1);
    public static Vector2 down = new Vector2(0, -1);

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Normalizes the Vector2
     * 
     * @return the normalized vector
     */
    public Vector2 normalize() {
        // If vector is a zero vector
        if (this.x == 0 && this.y == 0) return this;

        float length = getMagnitude();
        x = x / length;
        y = y / length;

        return this;
    }

    public float getMagnitude() {
        return (float) Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    /**
     *
     * @return x value of vector object.
     */
    public float getX() {
        return this.x;
    }

    /**
     * Adds two vectors together and returns the result
     * 
     * @param vec
     * @return
     */
    public Vector2 add(Vector2 vec) {
        this.x += vec.getX();
        this.y += vec.getY();
        return this;
    }

    public Vector2 subtract(Vector2 vec) {
        this.x -= vec.getX();
        this.y -= vec.getY();
        return this;
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
     * 
     * @param x
     * @param y
     * @return
     */
    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Vector2: (" + this.getX() + ", " + this.getY() + ")";
    }

    public Vector2 multiply(float scaleX, float scaleY) {
        return new Vector2(x * scaleX, y * scaleY);
    }
    public Vector2 multiply(float scalar) {
        return new Vector2(x * scalar, y * scalar);
    }
    public float distanceTo(Vector2 other) {
        float dx = other.x - this.x;
        float dy = other.y - this.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }
}
