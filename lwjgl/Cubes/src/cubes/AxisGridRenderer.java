/*
 * Public Domain
 */
package cubes;

import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class AxisGridRenderer {

	private AxisGridRenderer() {
	}

	public static void render() {
		// x
		for (float z = 0; z <= 1000; z += 10) {
			glColor3f(0, 0, 1);
			glBegin(GL_LINE);
			glVertex3f(0, 0, z);
			glVertex3f(1000, 0, z);
			glEnd();

			glBegin(GL_LINE);
			glVertex3f(z, 0, 0);
			glVertex3f(z, 0, 1000);
			glEnd();
		}

		// y
		for (float x = 0; x <= 1000; x += 10) {
			glColor3f(1, 0, 0);
			glBegin(GL_LINE);
			glVertex3f(x, 0, 0);
			glVertex3f(x, 1000, 0);
			glEnd();

			glBegin(GL_LINE);
			glVertex3f(0, x, 0);
			glVertex3f(1000, x, 0);
			glEnd();
		}

		// z
		for (float y = 0; y <= 1000; y += 10) {
			glColor3f(0, 1, 0);
			glBegin(GL_LINE);
			glVertex3f(0, y, 0);
			glVertex3f(0, y, 1000);
			glEnd();

			glBegin(GL_LINE);
			glVertex3f(0, 0, y);
			glVertex3f(0, 1000, y);
			glEnd();
		}
	}
}
