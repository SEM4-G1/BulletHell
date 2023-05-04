package dk.sdu.group.one.keyboard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import dk.sdu.group.one.player.ControllerService;

public class KeyBoardController implements ControllerService {

    @Override
    public ControllerScheme getInputs() {
        ControllerScheme controllerScheme = new ControllerScheme(
                isUp(),
                isDown(),
                isRight(),
                isLeft(),
                isStop()
        );
        return controllerScheme;
    }

    private boolean isRight() {
        return Gdx.input.isKeyPressed(Input.Keys.D);
    }

    private boolean isDown() {
        return Gdx.input.isKeyPressed(Input.Keys.S);
    }

    private boolean isUp() {
        return Gdx.input.isKeyPressed(Input.Keys.W);
    }

    private boolean isLeft() {
        return Gdx.input.isKeyPressed(Input.Keys.A);
    }

    private boolean isStop() {
        return Gdx.input.isKeyPressed(Input.Keys.SPACE);
    }
}