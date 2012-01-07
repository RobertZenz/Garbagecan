/*
 * Public Domain
 */
package bugandtreat;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Treat {

	private Color color = Color.green;
	private int x;
	private int y;
	private int size = 6;
	
	public Treat(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillRoundRect(x - size / 2, y - size / 2, size, size, 4);
	}
}
