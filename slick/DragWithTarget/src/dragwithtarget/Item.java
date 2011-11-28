/*
 * Public Domain
 */
package dragwithtarget;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public abstract class Item {

	public static final int BORDER_SIZE = 2;
	protected Color color;
	protected int height;
	protected int width;
	protected int x;
	protected int y;

	public Item(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}

	public abstract void render(Graphics g);

	public void renderBorder(Graphics g, Color color) {
		g.setColor(color);
		g.fillRect(
				x - BORDER_SIZE,
				y - BORDER_SIZE,
				width + BORDER_SIZE * 2,
				height + BORDER_SIZE * 2);
	}

	/**
	 * Check if the given coordinates are within the item.
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 * @return True if the coordiantes are within the item.
	 */
	public boolean isWithin(int x, int y) {
		return (x >= this.x && x <= this.x + this.width)
				&& (y >= this.y && y <= this.y + this.height);
	}
}
