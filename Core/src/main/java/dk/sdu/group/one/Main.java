package dk.sdu.group.one;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;


public class Main {

    public static void main(String[] args) {
        try {
            File file = new File("log.txt");
            file.createNewFile();
            System.setOut(new PrintStream("log.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Asteroids");
        config.setWindowSizeLimits(1920,1080,1920,1080);
        config.setWindowedMode(1920, 1080);
        new Lwjgl3Application(new CoreEngine(), config);
    }

}