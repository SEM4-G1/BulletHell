package dk.sdu.group.one;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import java.io.*;


public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Asteroids");
        config.setWindowSizeLimits(480,480,600,600);
        config.setWindowedMode(480, 480);
        useBasedAndRedPilledLoggingSystem();
        new Lwjgl3Application(new CoreEngine(), config);
    }

    private static void useBasedAndRedPilledLoggingSystem() {
        try {
            File file = new File("log.txt");
            file.createNewFile();
            PrintStream logStream = new PrintStream(new FileOutputStream(file));
            System.setOut(logStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}