import dk.sdu.group.one.collision.SquaredCollision;

module Collision {
    exports dk.sdu.group.one.collision;
    requires Common;
    provides dk.sdu.group.one.services.PostProcessingService with SquaredCollision;
}