package dk.sdu.group.one.data;

import dk.sdu.group.one.map.MapService;

public abstract class Entity {
    int x, y;

    int getX() { return this.x; }

    int getY() { return this.y; }

    public abstract void process();

    public abstract void start(MapService mapService);

}