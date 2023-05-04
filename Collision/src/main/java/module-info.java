module Collision {
    exports dk.sdu.group.one.collision;
    requires Common;
    provides dk.sdu.group.one.services.PostProcessingService with dk.sdu.group.one.collision.squaredCollision;
}