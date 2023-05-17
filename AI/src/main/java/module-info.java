module AI.AStar {
    exports dk.sdu.group.one.astar;
    requires Common;
    uses dk.sdu.group.one.services.LoggingService;
    provides dk.sdu.group.one.enemy.AI.AIservice with dk.sdu.group.one.astar.PathCreator;
    requires Enemy;
}