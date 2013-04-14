/*
 * Public Domain
 */
package smoothing;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Smoothing {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new World("NoiseWave"));
			app.setDisplayMode(1000, 700, false);
			app.setShowFPS(true);
			app.start();
		} catch (SlickException ex) {
			System.err.println(ex);
		}
	}
}
