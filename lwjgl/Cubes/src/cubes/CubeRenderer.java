/*
 * Public Domain
 */
package cubes;

import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class CubeRenderer {

	private CubeRenderer() {
	}

	public static void render(float x, float y, float z, float size) {
		float half = size / 2;

		glColor3f(0.5f, 0.5f, 0.5f);

		// Sides
		glNormal3f(0, 0, -1);
		glBegin(GL_QUADS);
		glVertex3f(x - half, y - half, z - half);
		glVertex3f(x - half, y - half, z + half);
		glVertex3f(x - half, y + half, z + half);
		glVertex3f(x - half, y + half, z - half);
		glEnd();

		glNormal3f(1, 0, 0);
		glBegin(GL_QUADS);
		glVertex3f(x + half, y + half, z - half);
		glVertex3f(x + half, y + half, z + half);
		glVertex3f(x + half, y - half, z + half);
		glVertex3f(x + half, y - half, z - half);
		glEnd();

		glNormal3f(0, 0, 1);
		glBegin(GL_QUADS);
		glVertex3f(x - half, y + half, z + half);
		glVertex3f(x + half, y + half, z + half);
		glVertex3f(x + half, y - half, z + half);
		glVertex3f(x - half, y - half, z + half);
		glEnd();

		glNormal3f(-1, 0, 0);
		glBegin(GL_QUADS);
		glVertex3f(x - half, y - half, z + half);
		glVertex3f(x - half, y - half, z - half);
		glVertex3f(x - half, y + half, z - half);
		glVertex3f(x - half, y + half, z + half);
		glEnd();

		// Bottom
		glNormal3f(0, -1, 0);
		glBegin(GL_QUADS);
		glVertex3f(x - half, y - half, z - half);
		glVertex3f(x + half, y - half, z - half);
		glVertex3f(x + half, y - half, z + half);
		glVertex3f(x - half, y - half, z + half);
		glEnd();

		// Top
		glNormal3f(0, 1, 0);
		glBegin(GL_QUADS);
		glVertex3f(x - half, y + half, z - half);
		glVertex3f(x - half, y + half, z + half);
		glVertex3f(x + half, y + half, z + half);
		glVertex3f(x + half, y + half, z - half);
		glEnd();
	}
}
