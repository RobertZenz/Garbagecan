/*
 * Public Domain
 */
package noisegenerator;

import noisegenerator.modifiers.DoubleWave;
import noisegenerator.generators.PureRandom;
import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class World {

	private static float speed = 0.3f;
	private boolean closeRequested;
	private Grid grid;
	private Generator generator;
	private Modifier modifier;
	private float distanceFromCenter = 50;
	private float rotationHorizontal = 0;
	private float rotationVertical = 45;

	public World(String title, int width, int height) throws LWJGLException {
		Display.setDisplayMode(new DisplayMode(width, height));
		Display.setTitle(title);
		Display.create();

		grid = new Grid(80, 80);

		generator = new PureRandom();
		generator.init(0, 0, 5);
		grid.generate(generator);

		modifier = new DoubleWave(3f);
	}

	public void run() {
		// Really needed?
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();

		gluPerspective(45, Display.getDisplayMode().getWidth() / (float) Display.getDisplayMode().getHeight(), 0, 100);
		resetEye();

		glEnable(GL_COLOR_MATERIAL);
		//glEnable(GL_CULL_FACE);
		
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		glShadeModel(GL_SMOOTH);
		glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE);

		while (!Display.isCloseRequested() && !closeRequested) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

			processKeyboard();
			processMouse();

			grid.render();

			// X - Blue
			glColor3f(0, 0, 1);
			glBegin(GL_LINE);
			glVertex3f(0, 0, 0);
			glVertex3f(1000, 0, 0);
			glEnd();

			// Y - Red
			glColor3f(1, 0, 0);
			glBegin(GL_LINE);
			glVertex3f(0, 0, 0);
			glVertex3f(0, 1000, 0);
			glEnd();

			// Z - Green
			glColor3f(0, 1, 0);
			glBegin(GL_LINE);
			glVertex3f(0, 0, 0);
			glVertex3f(0, 0, 1000);
			glEnd();

			Display.update();
		}

		Display.destroy();
	}

	private void processKeyboard() {
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			rotationHorizontal += speed;
			resetEye();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			rotationHorizontal -= speed;
			resetEye();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			rotationVertical -= speed;
			resetEye();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			rotationVertical += speed;
			resetEye();
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			grid.modify(modifier);
		}

		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
					closeRequested = true;
				} else if (Keyboard.getEventKey() == Keyboard.KEY_R) {
					grid.generate(generator);
				} else if (Keyboard.getEventKey() == Keyboard.KEY_RETURN) {
					grid.modify(modifier);
				}
			}
		}
	}

	public void processMouse() {
		int wheel;
		if ((wheel = Mouse.getDWheel()) != 0) {
			distanceFromCenter -= wheel / 50 * speed;
			resetEye();
		}
		if (Mouse.isButtonDown(0)) {
			rotationHorizontal += Mouse.getDX() * speed;
			rotationVertical += Mouse.getDY() * speed;
			resetEye();

			Mouse.setGrabbed(true);
		} else {
			Mouse.setGrabbed(false);
		}
	}

	private void resetEye() {
		if (rotationHorizontal > 360) {
			rotationHorizontal -= 360;
		}
		if (rotationHorizontal < 0) {
			rotationHorizontal += 360;
		}
		if (rotationVertical < 1) {
			rotationVertical = 1;
		}
		if (rotationVertical > 90) {
			rotationVertical = 90;
		}

		double phi = Math.toRadians(rotationVertical);
		double theta = Math.toRadians(rotationHorizontal);

		double x = distanceFromCenter * Math.cos(theta) * Math.sin(phi);
		double y = distanceFromCenter * Math.cos(phi);
		double z = distanceFromCenter * Math.sin(theta) * Math.sin(phi);

		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		gluLookAt((float) x, (float) y, (float) z, 0, 0, 0, 0, 1, 0);
	}
}
