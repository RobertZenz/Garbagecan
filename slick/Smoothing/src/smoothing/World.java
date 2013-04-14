/*
 * Public Domain
 */
package smoothing;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class World extends BasicGame {

	private int size = 10;
	private int width;
	private int height;
	private Square[] squares;
	boolean drawGrid = false;

	public World(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		width = container.getWidth() / size;
		height = container.getHeight() / size;
		squares = new Square[height * width];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				squares[width * y + x] = new Square(x, y);
			}
		}
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Square square = squares[width * y + x];
				Square[] neighbors = square.getNeighbors();
				/*
				 * 0 1 2
				 * 3 X 5
				 * 6 7 8
				 */
				neighbors[0] = getSquare(x - 1, y - 1);
				neighbors[1] = getSquare(x, y - 1);
				neighbors[2] = getSquare(x + 1, y - 1);
				neighbors[3] = getSquare(x - 1, y);
				neighbors[5] = getSquare(x + 1, y);
				neighbors[6] = getSquare(x - 1, y + 1);
				neighbors[7] = getSquare(x, y + 1);
				neighbors[8] = getSquare(x + 1, y + 1);
			}
		}
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();

		if (input.isMouseButtonDown(0)) {
			Square square = getSquare(input.getMouseX() / size, input.getMouseY() / size);
			if (square != null) {
				square.setFlipped(true);
			}
		}

		if (input.isKeyPressed(Input.KEY_SPACE)) {
			drawGrid = !drawGrid;
		}

		if (input.isKeyPressed(Input.KEY_RETURN)) {
			Square.triangleSmooth = !Square.triangleSmooth;
		}

		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.setBackground(Color.lightGray);

		g.setColor(Color.darkGray);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Square square = getSquare(x, y);
				if (square != null && square.isFlipped()) {
					square.draw(g, x * size, y * size, size);
				}
			}
		}

		if (drawGrid) {
			g.setColor(Color.blue);

			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					g.drawRect(x * size, y * size, size, size);
				}
			}
		}
	}

	private Square getSquare(int x, int y) {
		if (x >= 0 && x < width && y >= 0 && y < height) {
			return squares[width * y + x];
		}

		return null;
	}
}
