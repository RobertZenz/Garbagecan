/*
 * Public Domain
 */
package dotgenerator;

import java.util.List;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class World extends BasicGame {

	int count = 500;
	int pointSize = 5;
	long seed = 0;
	Generator generator;
	List<Vector2f> points;
	Vector2f lastMousePosition = new Vector2f(0, 0);
	Vector2f viewOffset = new Vector2f(0, 0);
	float zoom = 1f;
	
	public World(String name) {
		super(name);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		generator = new Generator(count, container.getHeight(), seed, container.getWidth());
		points = generator.randomDistribution();
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();

		if (input.isMouseButtonDown(0)) {
			if (container.isMouseGrabbed()) {
				viewOffset.x += (lastMousePosition.x - input.getMouseX());
				viewOffset.y += (lastMousePosition.y - input.getMouseY());
			}

			lastMousePosition.x = input.getMouseX();
			lastMousePosition.y = input.getMouseY();
			container.setMouseGrabbed(true);
		} else {
			container.setMouseGrabbed(false);
		}

		if (input.isKeyPressed(Input.KEY_1)) {
			points = generator.randomDistribution();
		} else if (input.isKeyPressed(Input.KEY_2)) {
			points = generator.randomEvenlyDistribution();
		} else if (input.isKeyPressed(Input.KEY_3)) {
			points = generator.randomEllipse();
		}

		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			container.exit();
		}
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		g.scale(zoom, zoom);
		g.translate(viewOffset.x, viewOffset.y);
		
		g.setBackground(Color.black);
		g.setColor(Color.white);

		if (points != null) {
			for (Vector2f point : points) {
				g.fillOval(point.x - (pointSize / 2), point.y - (pointSize / 2), pointSize, pointSize);
			}
		}
	}
}
