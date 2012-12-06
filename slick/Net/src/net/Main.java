/*
 * Public Domain
 */
package net;

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
		World world = new World("Net");


		try {
			AppGameContainer app = new AppGameContainer(world);
			app.setAlwaysRender(true);
			app.setDisplayMode(1280, 800, true);
			app.setTargetFrameRate(60);
			app.setShowFPS(true);
			app.start();
		} catch (SlickException ex) {
			System.err.println(ex);
		}

	}
}
