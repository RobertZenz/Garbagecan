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
	private float corners[][];

	public Cube(float x, float y, float z, float width, float height, float depth) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.width = width;
		this.height = height;
		this.depth = depth;

		this.corners = new float[][]{
					new float[]{x, y, z}, // 0
					new float[]{x + width, y, z}, // 1
					new float[]{x + width, y, z + depth}, // 2
					new float[]{x, y, z + depth}, // 3
					new float[]{x, y + height, z}, // 4
					new float[]{x + width, y + height, z}, // 5
					new float[]{x + width, y + height, z + depth}, // 6
					new float[]{x, y + height, z + depth} // 7
				};
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
		renderSide(0, 4, 5, 1);

		// -x
		glColor3f(0, 0, 1);
		glNormal3f(-1, 0, 0);
		renderSide(4, 0, 3, 7);

		// z
		glColor3f(0, 0, 1);
		glNormal3f(0, 0, 1);
		renderSide(3, 2, 6, 7);

		// x
		glColor3f(0, 0, 1);
		glNormal3f(1, 0, 0);
		renderSide(2, 1, 5, 6);

		// Bottom
		glColor3f(1, 0, 0);
		glNormal3f(0, -1, 0);
		renderSide(0, 1, 2, 3);

		// Top
		glColor3f(0, 1, 0);
		glBegin(GL_QUADS);
		glNormal3f(0, 1, 0);
		renderSide(7, 6, 5, 4);

		glPopMatrix();
	}

	public void renderSide(int corner1, int corner2, int corner3, int corner4) {
		glBegin(GL_QUADS);
		glVertex3f(corners[corner1][0], corners[corner1][1], corners[corner1][2]);
		glVertex3f(corners[corner2][0], corners[corner2][1], corners[corner2][2]);
		glVertex3f(corners[corner3][0], corners[corner3][1], corners[corner3][2]);
		glVertex3f(corners[corner4][0], corners[corner4][1], corners[corner4][2]);
		glEnd();
	}
}
