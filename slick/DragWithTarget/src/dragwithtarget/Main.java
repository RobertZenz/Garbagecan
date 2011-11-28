/*
 * Public Domain
 */
package dragwithtarget;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Main {

	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new Environment("Drag With Target"));
			app.setDisplayMode(800, 600, false);
			app.setShowFPS(true);
			app.setTargetFrameRate(60);
			app.start();
		} catch (SlickException ex) {
			System.err.println(ex);
			System.exit(0);
		}
	}
}
