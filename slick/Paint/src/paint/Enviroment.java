/*
 * Public Domain
 */
package paint;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Enviroment extends BasicGame {

	private Image buffer;
	private Graphics canvas;
	private int brushSize = 15;

	public Enviroment(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		buffer = new Image(container.getWidth(), container.getHeight());
		canvas = buffer.getGraphics();
		canvas.setBackground(Color.white);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();

		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();

		if (input.isKeyDown(Input.KEY_ADD)) {
			brushSize++;
		}
		if (input.isKeyDown(Input.KEY_MINUS)) {
			brushSize--;
			if (brushSize < 0) {
				brushSize = 0;
			}
		}

		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			canvas.setColor(Color.red);
			canvas.fillOval(mouseX - brushSize / 2, mouseY - brushSize / 2, brushSize, brushSize);
			canvas.flush();
		}
		if (input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON)) {
			canvas.setColor(Color.white);
			canvas.fillOval(mouseX - brushSize / 2, mouseY - brushSize / 2, brushSize, brushSize);
			canvas.flush();
		}

		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			container.exit();
		}
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		g.drawImage(buffer, 0, 0);

		int mouseX = container.getInput().getMouseX();
		int mouseY = container.getInput().getMouseY();

		g.setColor(Color.black);
		g.drawOval(mouseX - brushSize / 2, mouseY - brushSize / 2, brushSize, brushSize);
	}
}
