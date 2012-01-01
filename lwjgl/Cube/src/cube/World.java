/*
 * Public Domain
 */
package cube;

import org.lwjgl.input.Mouse;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.Point;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class World {

	private static float rotationSpeed = 0.5f;
	private boolean closeRequested;
	private Light light;
	private Cube cube;
	private Point lastMouseLocation;

	public World(String title, int width, int height) throws LWJGLException {
		Display.setDisplayMode(new DisplayMode(width, height));
		Display.setTitle(title);
		Display.create();

		light = new Light(1, 1, 1, -300, 300, 300);
		cube = new Cube(-100, -100, -100, 200, 200, 200);
	}

	public void run() {
		// One time setup
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(45, 800 / 600, 0, 100);
		gluLookAt(0, 0, 600, 0, 0, 0, 0, 1, 0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		glEnable(GL_COLOR_MATERIAL);
		glEnable(GL_CULL_FACE);
		glEnable(GL_LIGHTING);

		glShadeModel(GL_SMOOTH);
		glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE);

		light.init();

		while (!Display.isCloseRequested() && !closeRequested) {
			// Clear scene
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

			// Render stuff
			cube.render();

			// Input
			processKeyboard();
			processMouse();

			// Update
			Display.update();
		}

		Display.destroy();
	}

	public void processKeyboard() {
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			cube.rotateX(-rotationSpeed);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			cube.rotateX(rotationSpeed);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			cube.rotateY(-rotationSpeed);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			cube.rotateY(rotationSpeed);
		}

		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				switch (Keyboard.getEventKey()) {
					case Keyboard.KEY_F1:
						setCulling(true);
						break;
					case Keyboard.KEY_F2:
						setCulling(false);
						break;

					case Keyboard.KEY_F3:
						setSolidPolygons(true);
						break;
					case Keyboard.KEY_F4:
						setSolidPolygons(false);
						break;

					case Keyboard.KEY_F5:
						setLighting(true);
						break;
					case Keyboard.KEY_F6:
						setLighting(false);
						break;

					case Keyboard.KEY_ESCAPE:
						closeRequested = true;
						break;

				}
			}
		}
	}

	public void processMouse() {
		if (Mouse.isButtonDown(0)) {
			Mouse.setGrabbed(true);
			if (lastMouseLocation != null) {
				cube.rotateX((Mouse.getX() - lastMouseLocation.getX()) * rotationSpeed);
				cube.rotateY((lastMouseLocation.getY() - Mouse.getY()) * rotationSpeed);
			}

			lastMouseLocation = new Point(Mouse.getX(), Mouse.getY());
		} else {
			Mouse.setGrabbed(false);
			lastMouseLocation = null;
		}
	}

	public void setCulling(boolean culling) {
		if (culling) {
			glEnable(GL_CULL_FACE);
		} else {
			glDisable(GL_CULL_FACE);
		}
	}

	public void setLighting(boolean lighting) {
		if (lighting) {
			glEnable(GL_LIGHTING);
		} else {
			glDisable(GL_LIGHTING);
		}
	}

	public void setSolidPolygons(boolean solidPolygons) {
		if (solidPolygons) {
			glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
		} else {
			glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		}
	}
}
