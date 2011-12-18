/*
 * Public Domain
 */
package gameoflife2;

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

	private PetriDish petriDish;
	private int dishWidth;
	private int dishHeight;
	private int cellSize;
	private boolean showInfo = true;
	private boolean showGrid = true;

	public Game(String title, int dishWidth, int dishHeight) {
		super(title);

		this.dishWidth = dishWidth;
		this.dishHeight = dishHeight;
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		petriDish = new PetriDish(dishWidth, dishHeight);
		cellSize = Math.min(container.getWidth() / dishWidth, container.getHeight() / dishHeight);

		petriDish.init();
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();

		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)
				|| input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON)) {
			int x = input.getMouseX() / cellSize;
			int y = input.getMouseY() / cellSize;

			// Check bounds
			x = Math.max(x, 0);
			x = Math.min(x, petriDish.getWidth() - 1);
			y = Math.max(y, 0);
			y = Math.min(y, petriDish.getHeight() - 1);

			petriDish.getCell(x, y).setValue(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON));
		}

		if (input.isKeyPressed(Input.KEY_F1)) {
			showInfo = !showInfo;
		}
		if (input.isKeyPressed(Input.KEY_F2)) {
			showGrid = !showGrid;
		}

		if (input.isKeyDown(Input.KEY_SPACE)) {
			petriDish.doEvolution();
		}
		if (input.isKeyPressed(Input.KEY_RETURN)) {
			petriDish.doEvolution();
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		g.setBackground(Color.white);

		g.setColor(Color.black);
		for (int x = 0; x < petriDish.getWidth(); x++) {
			for (int y = 0; y < petriDish.getHeight(); y++) {
				if (showGrid) {
					g.drawRect(x * cellSize, y * cellSize, cellSize, cellSize);
				}

				if (petriDish.getCell(x, y).getValue()) {
					g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
				}
			}
		}

		if (showInfo) {
			g.setColor(Color.darkGray);
			g.drawString("FPS: " + container.getFPS(), 5, 5);
			g.drawString("Generation: " + Long.toString(petriDish.getGeneration()), 5, 20);
			g.drawString("Duration: " + Long.toString(petriDish.getDuration()), 5, 35);
			g.drawString("F1 - Hide/Show this information", 5, 55);
			g.drawString("F2 - Hide/Show grid", 5, 70);
		}
	}
}
