/*
 * Public Domain
 */
package cube;

import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Cube {

	private float x;
	private float y;
	private float z;
	private float width;
	private float height;
	private float depth;
	private float rotationX;
	private float rotationY;

	public Cube(float x, float y, float z, float width, float height, float depth) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.width = width;
		this.height = height;
		this.depth = depth;
	}

	public void rotateX(float rotation) {
		this.rotationX += rotation;

		while (this.rotationX < 0) {
			rotationX += 360;
		}
		while (this.rotationX > 360) {
			rotationX -= 360;
		}
	}

	public void rotateY(float rotation) {
		this.rotationY += rotation;

		while (this.rotationY < 0) {
			rotationY += 360;
		}
		while (this.rotationY > 360) {
			rotationY -= 360;
		}
	}

	public void render() {
		glColor3f(1, 1, 1);

		glPushMatrix();
		glRotatef(rotationY, 1, 0, 0);
		glRotatef(rotationX, 0, 1, 0);

		//         -z
		//          ^
		//       +-----+
		//       |  |  |
		//       |  |  |
		//   -x <|--+--|> x
		//       |  |  |
		//       |  |  |
		//       +-----+
		//          v
		//          z

		// -z
		glColor3f(0, 0, 1);
		glNormal3f(0, 0, -1);
		glBegin(GL_QUADS);
		glVertex3f(x, y + height, z);
		glVertex3f(x + width, y + height, z);
		glVertex3f(x + width, y, z);
		glVertex3f(x, y, z);
		glEnd();

		// -x
		glColor3f(0, 0, 1);
		glNormal3f(-1, 0, 0);
		glBegin(GL_QUADS);
		glVertex3f(x, y, z);
		glVertex3f(x, y, z + depth);
		glVertex3f(x, y + height, z + depth);
		glVertex3f(x, y + height, z);
		glEnd();

		// z
		glColor3f(0, 0, 1);
		glNormal3f(0, 0, 1);
		glBegin(GL_QUADS);
		glVertex3f(x, y, z + depth);
		glVertex3f(x + width, y, z + depth);
		glVertex3f(x + width, y + height, z + depth);
		glVertex3f(x, y + height, z + depth);
		glEnd();

		// x
		glColor3f(0, 0, 1);
		glNormal3f(1, 0, 0);
		glBegin(GL_QUADS);
		glVertex3f(x + width, y + height, z);
		glVertex3f(x + width, y + height, z + depth);
		glVertex3f(x + width, y, z + depth);
		glVertex3f(x + width, y, z);
		glEnd();

		// Bottom
		glColor3f(1, 0, 0);
		glNormal3f(0, -1, 0);
		glBegin(GL_QUADS);
		glVertex3f(x + width, y, z);
		glVertex3f(x + width, y, z + depth);
		glVertex3f(x, y, z + depth);
		glVertex3f(x, y, z);
		glEnd();

		// Top
		glColor3f(0, 1, 0);
		glBegin(GL_QUADS);
		glNormal3f(0, 1, 0);
		glVertex3f(x, y + height, z);
		glVertex3f(x, y + height, z + depth);
		glVertex3f(x + width, y + height, z + depth);
		glVertex3f(x + width, y + height, z);
		glEnd();

		glPopMatrix();
	}

	private void renderSide(float x, float y, float z, float width, float height, float deth) {
	}
}
