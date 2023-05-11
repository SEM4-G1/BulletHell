module Obstacle {
    exports dk.sdu.group.one.rock;
    requires Common;

    provides dk.sdu.group.one.data.Entity with dk.sdu.group.one.rock.Rock;
}