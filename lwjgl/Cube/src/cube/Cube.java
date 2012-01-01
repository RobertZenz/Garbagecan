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
	private Polygon[] polygons;

	public Cube(float x, float y, float z, float width, float height, float depth) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.width = width;
		this.height = height;
		this.depth = depth;

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

		polygons = new Polygon[]{
					new Polygon(corners[0], corners[4], corners[5], corners[1]),
					new Polygon(corners[4], corners[0], corners[3], corners[7]),
					new Polygon(corners[3], corners[2], corners[6], corners[7]),
					new Polygon(corners[2], corners[1], corners[5], corners[6]),
					new Polygon(corners[0], corners[1], corners[2], corners[3]),
					new Polygon(corners[7], corners[6], corners[5], corners[4])
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
		glPushMatrix();
		glRotatef(rotationY, 1, 0, 0);
		glRotatef(rotationX, 0, 1, 0);

		polygons[0].render(0, 0, 1);
		polygons[1].render(0, 0, 1);
		polygons[2].render(0, 0, 1);
		polygons[3].render(0, 0, 1);

		polygons[4].render(1, 0, 0);
		polygons[5].render(0, 1, 0);

		glPopMatrix();
	}
}
