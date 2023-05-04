package dk.sdu.group.one.player;

public interface ControllerService {
    ControllerScheme getInputs();
    record ControllerScheme(boolean isUp, boolean isDown, boolean isRight, boolean isLeft, boolean isStop) {}
}
