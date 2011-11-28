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
public class DragTarget extends Item {

	public DragTarget(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
	}

	public void render(Graphics g) {
		renderBorder(g, Color.red);
		g.setColor(Color.black);
		g.fillRect(x, y, width, height);
	}
}
