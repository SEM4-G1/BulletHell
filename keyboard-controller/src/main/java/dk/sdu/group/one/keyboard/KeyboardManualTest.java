package dk.sdu.group.one.keyboard;

import dk.sdu.group.one.player.ControllerService;

public class KeyboardManualTest {
    public static void main(String[] args) {
        ControllerService controllerService = new KeyboardController();
        while (true) {
            System.out.println(controllerService.getInputs());
        }
    }
}