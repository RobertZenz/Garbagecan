/*
 * Public Domain
 */
package noisegenerator;

import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Grid {

	private float values[];
	private int width;
	private int depth;

	public Grid(int width, int depth) {
		this.width = width;
		this.depth = depth;

		values = new float[width * depth];
	}

	public void generate(Generator generator) {
		for (int idxX = 0; idxX < width; idxX++) {
			for (int idxZ = 0; idxZ < depth; idxZ++) {
				values[idxZ * width + idxX] = generator.getValue(idxX, idxZ);
			}
		}
	}

	public void modify(Modifier modifier) {
		modifier.modify(values, width, depth);
	}

	public void render() {
		for (int idxX = 0; idxX < width - 1; idxX++) {
			for (int idxZ = 0; idxZ < depth - 1; idxZ++) {
				renderQuad(idxX, idxZ);
			}
		}
	}

	private void renderQuad(int idxX, int idxZ) {
		float x = idxX - width / 2 + 0.5f;
		float z = idxZ - depth / 2 + 0.5f;

		
		glColor3f(0, 1, 1);

		glBegin(GL_TRIANGLES);
		glVertex3f(x, values[idxZ * width + idxX], z);
		glVertex3f(x, values[(idxZ + 1) * width + idxX], z + 1f);
		glVertex3f(x + 1f, values[(idxZ + 1) * width + (idxX + 1)], z + 1f);
		glEnd();

		glBegin(GL_TRIANGLES);
		glVertex3f(x + 1f, values[(idxZ + 1) * width + (idxX + 1)], z + 1f);
		glVertex3f(x + 1f, values[idxZ * width + (idxX + 1)], z);
		glVertex3f(x, values[idxZ * width + idxX], z);
		glEnd();
	}
}
