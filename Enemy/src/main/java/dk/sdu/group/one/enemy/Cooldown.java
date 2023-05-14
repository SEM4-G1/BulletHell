package dk.sdu.group.one.enemy;

public class Cooldown{
    private float time;
    private float maxTime;

    public Cooldown(float maxTime) {
        this.maxTime = maxTime;
    }

    public void update(double dt) {
        time += dt;
    }

    public void reset() {
        time = 0;
    }

    public boolean isReady() {
        return time >= maxTime;
    }
}
