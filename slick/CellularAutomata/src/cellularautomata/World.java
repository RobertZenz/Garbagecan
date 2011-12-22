/*
 * Public Domain
 */
package cellularautomata;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class World extends BasicGame {

	Map map;
	int pointWidth;
	int pointHeight;

	public World(String title, int width, int height) {
		super(title);

		map = new Map(width, height);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		map.init();
		map.fillWithRandom(0);

		pointWidth = container.getWidth() / map.getWidth();
		pointHeight = container.getHeight() / map.getHeight();
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();

		if (input.isKeyPressed(Input.KEY_RETURN)) {
			map.doIteration();
		}
		if (input.isKeyDown(Input.KEY_SPACE)) {
			map.doIteration();
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		for (int idxX = 0; idxX < map.getWidth(); idxX++) {
			for (int idxY = 0; idxY < map.getHeight(); idxY++) {
				g.setColor(map.getColor(idxX, idxY));
				g.fillRect(idxX * pointWidth, idxY * pointHeight, pointWidth, pointHeight);
			}
		}
	}
}
