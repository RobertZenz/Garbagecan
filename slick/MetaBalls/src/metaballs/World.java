/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metaballs;

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

	private Vector2f point = new Vector2f(0, 0);
	private float radius = 75;

	public World(String name) {
		super(name);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();

		if (input.isMouseButtonDown(0)) {
			point.x = input.getMouseX();
			point.y = input.getMouseY();
		}

		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			container.exit();
		}
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		g.setBackground(Color.black);
		for (int x = 0; x < container.getScreenWidth(); x++) {
			for (int y = 0; y < container.getScreenHeight(); y++) {
				float value = 0;
				float distance = (float) Math.pow(Math.sqrt(Math.pow(x - point.x, 2) + Math.pow(y - point.y, 2)), 2);
				//value = (float) (1 / (Math.pow(x - point.x, 2) + Math.pow(y - point.y, 2)));
				if (distance <= 450) {
					value = (float) Math.pow(1 - distance / 450, 2);
				}

				g.setColor(new Color(value, value, value));
				g.fillRect(x, y, 1, 1);
			}
		}
	}
}
