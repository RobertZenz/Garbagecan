/*
 * Public Domain
 */
package cubes;

import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Light {

	private Light() {
	}

	public static void init(float x, float y, float z, float red, float green, float blue) {
		FloatBuffer position = BufferUtils.createFloatBuffer(4);
		position.put(x).put(y).put(z).put(1).flip();

		FloatBuffer color = BufferUtils.createFloatBuffer(4);
		color.put(red).put(green).put(blue).put(0).flip();

		glLight(GL_LIGHT0, GL_POSITION, position);
		glLight(GL_LIGHT0, GL_COLOR, color);
		glEnable(GL_LIGHT0);
	}
}
