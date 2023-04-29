package dk.sdu.group.one;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;


public class Main {

    public static void main(String[] args) {

        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Asteroids");
        config.setWindowSizeLimits(1980,1080,1980,1980);
        config.setWindowedMode(1980, 1080);
        new Lwjgl3Application(new CoreEngine(), config);
    }

}