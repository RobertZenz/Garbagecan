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
		glColor3f(0.5f, 0.5f, 0.5f);

		// Sides
		glNormal3f(0, 0, -1);
		glBegin(GL_QUADS);
		glVertex3f(x + size, y , z );
		glVertex3f(x , y , z );
		glVertex3f(x , y + size, z );
		glVertex3f(x + size, y + size, z );
		glEnd();

		glNormal3f(1, 0, 0);
		glBegin(GL_QUADS);
		glVertex3f(x + size, y + size, z );
		glVertex3f(x + size, y + size, z + size);
		glVertex3f(x + size, y , z + size);
		glVertex3f(x + size, y , z );
		glEnd();

		glNormal3f(0, 0, 1);
		glBegin(GL_QUADS);
		glVertex3f(x + size, y + size, z + size);
		glVertex3f(x , y + size, z + size);
		glVertex3f(x , y , z + size);
		glVertex3f(x + size, y , z + size);
		glEnd();

		glNormal3f(-1, 0, 0);
		glBegin(GL_QUADS);
		glVertex3f(x , y , z );
		glVertex3f(x , y , z + size);
		glVertex3f(x , y + size, z + size);
		glVertex3f(x , y + size, z );
		glEnd();

		// Bottom
		glNormal3f(0, -1, 0);
		glBegin(GL_QUADS);
		glVertex3f(x , y , z );
		glVertex3f(x + size, y , z );
		glVertex3f(x + size, y , z + size);
		glVertex3f(x , y , z + size);
		glEnd();

		// Top
		glNormal3f(0, 1, 0);
		glBegin(GL_QUADS);
		glVertex3f(x , y + size, z );
		glVertex3f(x , y + size, z + size);
		glVertex3f(x + size, y + size, z + size);
		glVertex3f(x + size, y + size, z );
		glEnd();
	}
}
