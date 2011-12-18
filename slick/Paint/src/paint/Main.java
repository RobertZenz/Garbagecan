/*
 * Public Domain
 */

package paint;

import java.util.logging.Level;
import java.util.logging.Logger;
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
			AppGameContainer app = new AppGameContainer(new Enviroment("Paint"));
			app.setDisplayMode(800, 600, false);
			app.setShowFPS(true);
			app.setTargetFrameRate(60);
			app.start();
		} catch (SlickException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
    }

}
