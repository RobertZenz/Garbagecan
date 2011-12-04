/*
 * Public Domain
 */
package isometricworld.tiles;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class ColoredTile extends Tile {

	private Color color;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public ColoredTile(int width, int height, Color color) throws SlickException {
		super(width, height);
		this.color = color;
		redraw();
	}

	@Override
	public final void redraw() throws SlickException {
		Graphics g = image.getGraphics();
		g.setColor(color);
		g.fill(innerArea);
		g.setColor(Color.black);
		g.draw(innerArea);
		g.flush();
	}

	@Override
	public Shape getHighlightShape() {
		return innerArea;
	}
}
