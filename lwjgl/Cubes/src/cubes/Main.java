/*
 * Public Domain
 */
package cubes;

import org.lwjgl.LWJGLException;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Main {

	public static void main(String[] args) {
		try {
			World world = new World("Cubes", 1200, 700);
			world.run();
		} catch (LWJGLException ex) {
			System.err.println(ex);
		}
	}
}
