/*
 * Public Domain
 */

package bugandtreat;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Main {

    public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new World("Bug And Treat"));
			app.setDisplayMode(1200, 700, false);
			app.setShowFPS(true);
			app.setTargetFrameRate(60);
			app.start();
		} catch (SlickException ex) {
			System.err.println(ex);
		}
    }
}
