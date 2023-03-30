package java.sdu.dk.group.one;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import java.sdu.dk.group.one.CoreEngine;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main(String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("bullethell");
		new Lwjgl3Application(new CoreEngine(), config);
	}

	public static int add(int a, int b){
		return a+b;
	}
}