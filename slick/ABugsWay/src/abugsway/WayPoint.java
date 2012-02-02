/*
 * Public Domain
 */
package abugsway;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class WayPoint {

	private static final int SIZE = 8;
	private static final Color COLOR = Color.green;
	private int x;
	private int y;

	public WayPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		g.setColor(COLOR);
		g.fillOval(x - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
	}
}
