/*
 * Public Domain
 */
package cubes;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Viewer {

	public static float MOVE_SPEED = 0.5f;
	private float eyeX;
	private float eyeY;
	private float eyeZ;
	private Vector3f lookAt;

	public Viewer(float eyeX, float eyeY, float eyeZ, float targetX, float targetY, float targetZ) {
		this.eyeX = eyeX;
		this.eyeY = eyeY;
		this.eyeZ = eyeZ;

		this.lookAt = new Vector3f(targetX, targetY, targetZ);
	}

	public void moveEye(float modX, float modY, float modZ) {
		eyeX += modX;
		eyeY += modY;
		eyeZ += modZ;
	}

	public void moveBackward() {
		eyeX -= lookAt.getX() * MOVE_SPEED;
		eyeY -= lookAt.getY() * MOVE_SPEED;
		eyeZ -= lookAt.getZ() * MOVE_SPEED;
	}

	public void moveForward() {
		eyeX += lookAt.getX() * MOVE_SPEED;
		eyeY += lookAt.getY() * MOVE_SPEED;
		eyeZ += lookAt.getZ() * MOVE_SPEED;
	}

	public void update() {
		GL11.glLoadIdentity();
		GLU.gluLookAt(eyeX, eyeY, eyeZ, eyeX + lookAt.getX(), eyeY + lookAt.getY(), eyeZ + lookAt.getZ(), 0, 1, 0);
	}
}
