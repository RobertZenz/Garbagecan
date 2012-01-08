/*
 * Public Domain
 */
package cubes;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Viewer {

	private float eyeX;
	private float eyeY;
	private float eyeZ;
	private float targetX;
	private float targetY;
	private float targetZ;

	public Viewer(float eyeX, float eyeY, float eyeZ, float targetX, float targetY, float targetZ) {
		this.eyeX = eyeX;
		this.eyeY = eyeY;
		this.eyeZ = eyeZ;
		this.targetX = targetX;
		this.targetY = targetY;
		this.targetZ = targetZ;
	}

	public void moveEye(float modX, float modY, float modZ) {
		eyeX += modX;
		eyeY += modY;
		eyeZ += modZ;

		moveTarget(modX, modY, modZ);
	}

	public void moveTarget(float modX, float modY, float modZ) {
		targetX += modX;
		targetY += modY;
		targetZ += modZ;
	}

	public void update() {
		GL11.glLoadIdentity();
		GLU.gluLookAt(eyeX, eyeY, eyeZ, targetX, targetY, targetZ, 0, 1, 0);
	}

	public void setEye(float x, float y, float z) {
		eyeX = x;
		eyeY = y;
		eyeZ = z;
	}

	public void setTarget(float x, float y, float z) {
		targetX = x;
		targetY = y;
		targetZ = z;
	}
}
