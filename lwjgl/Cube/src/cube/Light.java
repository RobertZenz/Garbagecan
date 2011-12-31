/*
 * Public Domain
 */
package cube;

import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Light {

	FloatBuffer position;
	FloatBuffer color;
	
	public Light(float red, float green, float blue, float x, float y, float z) {
		this.position = BufferUtils.createFloatBuffer(4);
		this.position.put(x).put(y).put(z).put(1).flip();

		this.color = BufferUtils.createFloatBuffer(4);
		this.color.put(red).put(green).put(blue).put(0).flip();
	}

	public void init() {
		glLight(GL_LIGHT0, GL_POSITION, position);
		glLight(GL_LIGHT0, GL_SPECULAR, color);
		glEnable(GL_LIGHT0);
	}
}
