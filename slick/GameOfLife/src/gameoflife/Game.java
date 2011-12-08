/*
 * Public Domain
 */
package gameoflife;

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
public class Game extends BasicGame {

	private boolean[] cells;
	private int width;
	private int height;
	private int cellSize;

	public Game(String title, int width, int height) {
		super(title);

		this.width = width;
		this.height = height;
		cells = new boolean[width * height];
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		cellSize = Math.min(container.getWidth() / width, container.getHeight() / height);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();

		if (input.isKeyPressed(Input.KEY_RETURN)) {
			doGeneration();
		}
		if (input.isKeyDown(Input.KEY_SPACE)) {
			doGeneration();
		}

		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			int x = input.getMouseX() / cellSize;
			int y = input.getMouseY() / cellSize;

			int idx = y * width + x;
			if (idx < cells.length
					&& x > 0 && x < width - 1
					&& y > 0 && y < height - 1) {
				cells[idx] = !cells[idx];
			}
		}

		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		g.setBackground(Color.white);
		g.setColor(Color.black);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				g.drawRect(x * cellSize, y * cellSize, cellSize, cellSize);
				if (cells[y * width + x]) {
					g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
				}
			}
		}
	}

	/**
	 * Evolve into the next nextGeneration.
	 */
	private void doGeneration() {
		boolean[] nextGeneration = new boolean[cells.length];
		
		for (int x = 1; x < width - 1; x++) {
			for (int y = 1; y < height - 1; y++) {
				int neighbors = 0;

				// Check surrounding cells.
				for (int neighborX = x - 1; neighborX <= x + 1; neighborX++) {
					for (int neighborY = y - 1; neighborY <= y + 1; neighborY++) {
						if (neighborX != x || neighborY != y) {
							if (cells[neighborY * width + neighborX]) {
								neighbors++;
							}
						}
					}
				}

				int idx = y * width + x;

				switch (neighbors) {
					case 0:
					case 1:
						nextGeneration[idx] = false;
						break;

					case 2:
						nextGeneration[idx] = cells[idx];
						break;

					case 3:
						nextGeneration[idx] = true;
						break;

					default:
						nextGeneration[idx] = false;
						break;
				}
			}
		}

		cells = nextGeneration;
	}
}
