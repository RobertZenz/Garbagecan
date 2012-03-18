/*
 * Public Domain
 */
package perspective;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Layer {

	private static final float MAIN_PLANE_DISTANCE = 100;
	private Color color;
	private int distance;
	private int height;
	private int width;
	private float x;
	private float y;

	public Layer(Color color, int width, int height, int distance) {
		this.color = color;
		this.width = width;
		this.height = height;
		this.distance = distance;
	}

	public void update(GameContainer container, int delta) {
		int mouseX = container.getInput().getMouseX();
		int mouseY = container.getInput().getMouseY();

		mouseX = container.getWidth() / 2 - mouseX;
		mouseY = container.getHeight() / 2 - mouseY;

		x = mouseX / MAIN_PLANE_DISTANCE * distance;
		y = mouseY / MAIN_PLANE_DISTANCE * distance;
		x = container.getWidth() / 2 - width / 2 + x;
		y = container.getHeight() / 2 - height / 2 + y;
	}

	public void render(GameContainer container, Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
}
