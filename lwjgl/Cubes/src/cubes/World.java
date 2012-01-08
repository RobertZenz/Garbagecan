/*
 * Public Domain
 */
package cubes;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class World {

	private boolean closeRequested;
	private Viewer viewer;

	public World(String title, int width, int height) throws LWJGLException {
		Display.setDisplayMode(new DisplayMode(width, height));
		Display.setTitle(title);
		Display.create();

		viewer = new Viewer(100, 100, 100, -1, -1, -1);
	}

	public void run() throws LWJGLException {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();

		gluPerspective(45, Display.getDisplayMode().getWidth() / (float) Display.getDisplayMode().getHeight(), 0, 1000);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		glEnable(GL_CULL_FACE);
		glEnable(GL_COLOR_MATERIAL);
		glEnable(GL_LIGHTING);

		glShadeModel(GL_SMOOTH);
		glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE);

		// Light
		Light.init(1000, 1000, 500, 1, 1, 1);

		while (!Display.isCloseRequested() && !closeRequested) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

			processKeyboard();
			processMouse();

			viewer.update();

			AxisRenderer.render();
			CubeRenderer.render(0, 0, 0, 50);

			Display.update();
		}

		Display.destroy();
	}

	private void processKeyboard() {
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			viewer.moveForward();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			viewer.moveBackward();
		}

		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
					closeRequested = true;
				}
			}
		}
	}

	private void processMouse() {
	}
}
