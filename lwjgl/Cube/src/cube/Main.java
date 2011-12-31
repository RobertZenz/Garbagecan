/*
 * Public Domain
 */
package cube;

import org.lwjgl.LWJGLException;

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
			World world = new World("Cube", 800, 600);
			world.run();
		} catch (LWJGLException ex) {
			System.err.println(ex);
		}
	}
}
