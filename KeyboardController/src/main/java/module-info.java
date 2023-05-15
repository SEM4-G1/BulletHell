import dk.sdu.group.one.keyboard.KeyboardController;

module KeyboardController {
    requires Player;
    requires com.badlogic.gdx;
    provides dk.sdu.group.one.player.ControllerService with KeyboardController;
}