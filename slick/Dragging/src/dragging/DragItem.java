/*
 * Public Domain
 */
package dragging;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * An item which can dragged across the screen.
 * @author Robert 'Bobby' Zenz
 */
public class DragItem {

	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	private boolean isMouseOver;
	private boolean isHeld;
	/**
	 * Offset to the mouse positition.
	 */
	private int offsetX;
	/**
	 * Offset to the mouse position.
	 */
	private int offsetY;

	public DragItem(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}

	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();

		// Check if the mouse button is pressed and
		// the mouse is either within the item or
		// the item is already dragged around.
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)
				&& (isWithin(input.getMouseX(), input.getMouseY()) || isHeld)) {
			if (!isHeld) {
				offsetX = x - input.getMouseX();
				offsetY = y - input.getMouseY();
			}
			isHeld = true;
		} else {
			isHeld = false;
		}

		// If the item is held by the mouse.
		if (isHeld) {
			x = input.getMouseX() + offsetX;
			y = input.getMouseY() + offsetY;
		} else {
			// If it is not held by the mouse,
			// at least check if the mouse hovers above.
			isMouseOver = isWithin(input.getMouseX(), input.getMouseY());
		}
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		if (isHeld) {
			g.setColor(Color.orange);
			g.fillRect(x - 2, y - 2, width + 4, height + 4);
		} else if (isMouseOver) {
			g.setColor(Color.cyan);
			g.fillRect(x - 2, y - 2, width + 4, height + 4);
		}

		g.setColor(color);
		g.fillRect(x, y, width, height);
	}

	/**
	 * Check if the mouse is within the rectangle.
	 * @param mouseX X-Location of the Mouse.
	 * @param mouseY Y-Location of the Mouse.
	 * @return True if the mouse is within the item.
	 */
	private boolean isWithin(int mouseX, int mouseY) {
		return (mouseX >= x && mouseX <= x + width)
				&& (mouseY >= y && mouseY <= y + height);
	}
}
