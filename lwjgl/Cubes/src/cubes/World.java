/*
 * Public Domain
 */
package cubes;

import org.lwjgl.input.Mouse;
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
	private Packet packet;

	public World(String title, int width, int height) throws LWJGLException {
		Display.setDisplayMode(new DisplayMode(width, height));
		Display.setTitle(title);
		Display.create();

		viewer = new Viewer(-100, -100, -100, 1, 1, 1);

		packet = new cubes.packets.RandomSizedCubes(0);
	}

	public void run() throws LWJGLException {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();

		gluPerspective(45, Display.getDisplayMode().getWidth() / (float) Display.getDisplayMode().getHeight(), 0.1f, 5000);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		glEnable(GL_CULL_FACE);
		glEnable(GL_COLOR_MATERIAL);
		glEnable(GL_LIGHTING);
		glEnable(GL_DEPTH_TEST);
		
		glShadeModel(GL_SMOOTH);
		glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE);
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
		
		// Light
		Light.init(1000, 1000, 500, 1, 1, 1);

		while (!Display.isCloseRequested() && !closeRequested) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

			processKeyboard();
			processMouse();

			viewer.update();

			AxisGridRenderer.render();

			packet.render();

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
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			viewer.moveLeft();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			viewer.moveRight();
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
		if (Mouse.isGrabbed()) {
			int dx = Mouse.getDX();
			if (dx > 0) {
				viewer.lookRight();
			} else if (dx < 0) {
				viewer.lookLeft();
			}

			int dy = Mouse.getDY();
			if (dy > 0) {
				viewer.lookUp();
			} else if (dy < 0) {
				viewer.lookDown();
			}
		}

		while (Mouse.next()) {
			if (Mouse.getEventButtonState()) {
				if (Mouse.getEventButton() == 0) {
					Mouse.setGrabbed(!Mouse.isGrabbed());

					// Reset the coordinates
					Mouse.getDX();
					Mouse.getDY();
				}
			}
		}
	}
}
