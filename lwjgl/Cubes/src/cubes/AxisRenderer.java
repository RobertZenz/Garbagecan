/*
 * Public Domain
 */
package cubes;

import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class AxisRenderer {

	private AxisRenderer() {
	}

	public static void render() {
		// x
		glColor3f(0, 0, 1);
		glBegin(GL_LINE);
		glVertex3f(0, 0, 0);
		glVertex3f(1000, 0, 0);
		glEnd();

		// y
		glColor3f(1, 0, 0);
		glBegin(GL_LINE);
		glVertex3f(0, 0, 0);
		glVertex3f(0, 1000, 0);
		glEnd();

		// z
		glColor3f(0, 1, 0);
		glBegin(GL_LINE);
		glVertex3f(0, 0, 0);
		glVertex3f(0, 0, 1000);
		glEnd();
	}
}
