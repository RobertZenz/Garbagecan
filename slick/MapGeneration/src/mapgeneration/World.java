/*
 * Public Domain
 */
package mapgeneration;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class World extends BasicGame {

	private Map map;

	public World(String title, int width, int height) {
		super(title);

		map = new Map(width, height);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		map.init();
		map.generate(0);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();

		if (input.isKeyPressed(Input.KEY_RETURN)) {
			map.doGeneration();
		}
		if (input.isKeyDown(Input.KEY_SPACE)) {
			map.doGeneration();
		}

		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		g.setBackground(Color.white);

		int tileWidth = container.getWidth() / map.getWidth();
		int tileHeight = container.getHeight() / map.getHeight();

		for (int idxX = 0; idxX < map.getWidth(); idxX++) {
			for (int idxY = 0; idxY < map.getHeight(); idxY++) {
				map.getTile(idxX, idxY).render(g, idxX * tileWidth, idxY * tileHeight, tileWidth, tileHeight);
				g.setColor(Color.black);
				//g.drawRect(idxX * tileWidth, idxY * tileHeight, tileWidth, tileHeight);
			}
		}

		g.setColor(Color.darkGray);
		g.drawString("FPS: " + Integer.toString(container.getFPS()), 5, 20);
	}
}
