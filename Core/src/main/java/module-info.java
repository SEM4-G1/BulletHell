module Core {
    requires Common;
    requires Player;
    requires java.desktop;
    requires com.badlogic.gdx;
    requires Weapon;
    requires Map;
    requires Enemy;
    requires Obstacle;
    uses dk.sdu.group.one.map.MapService;
    uses dk.sdu.group.one.services.LevelService;
}