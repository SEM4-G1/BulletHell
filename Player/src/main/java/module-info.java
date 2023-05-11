module Player {
    uses dk.sdu.group.one.player.ControllerService;
    exports dk.sdu.group.one.player;
    requires Common;

    provides dk.sdu.group.one.data.Entity with dk.sdu.group.one.player.Player;
}