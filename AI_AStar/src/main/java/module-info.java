module AI.AStar {
    exports dk.sdu.group.one.ai_astar;
    exports dk.sdu.group.one.ai_astar.helpers;
    requires Common;
    uses dk.sdu.group.one.services.LoggingService;
    provides dk.sdu.group.one.enemy.AI.AIservice with dk.sdu.group.one.ai_astar.PathCreator;
    requires Enemy;
}