/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mufugenerator;

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
			AppGameContainer app = new AppGameContainer(new World("MufuGenerator"));
			app.setDisplayMode(1000, 600, false);
			app.setShowFPS(true);
			app.setTargetFrameRate(60);
			app.start();
		} catch (SlickException ex) {
			System.err.println(ex);
		}
	}
}
