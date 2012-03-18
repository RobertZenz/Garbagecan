/*
 * Public Domain
 */
package perspective;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Main {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new World("Perspective"));
			app.setDisplayMode(1200, 700, false);
			app.setShowFPS(true);
			app.setSmoothDeltas(true);
			app.setTargetFrameRate(60);
			app.setVSync(true);
			app.start();
		} catch (SlickException ex) {
			System.err.println(ex);
		}
	}
}
