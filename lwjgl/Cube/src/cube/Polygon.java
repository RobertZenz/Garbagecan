/*
 * Public Domain
 */
package cube;

import org.lwjgl.util.vector.Vector3f;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Polygon {

	private float[][] corners;
	private Vector3f normal = new Vector3f();

	public Polygon(float[] corner1, float[] corner2, float[] corner3, float[] corner4) {
		corners = new float[][]{
					corner1,
					corner2,
					corner3,
					corner4
				};

		calculcateNormal();
	}

	public final void calculcateNormal() {
		Vector3f v1 = new Vector3f(corners[1][0] - corners[0][0], corners[1][1] - corners[0][1], corners[1][2] - corners[0][2]);
		Vector3f v2 = new Vector3f(corners[3][0] - corners[0][0], corners[3][1] - corners[0][1], corners[3][2] - corners[0][2]);

		normal.setX(v1.y * v2.z - v1.z * v2.y);
		normal.setY(v1.z * v2.x - v1.x * v2.z);
		normal.setZ(v1.x * v2.y - v1.y * v2.x);
		normal.normalise();
	}

	public void render(float r, float g, float b) {
		glColor3f(r, g, b);
		glNormal3f(normal.x, normal.y, normal.z);

		glBegin(GL_QUADS);
		glVertex3f(corners[0][0], corners[0][1], corners[0][2]);
		glVertex3f(corners[1][0], corners[1][1], corners[1][2]);
		glVertex3f(corners[2][0], corners[2][1], corners[2][2]);
		glVertex3f(corners[3][0], corners[3][1], corners[3][2]);
		glEnd();
	}
}
