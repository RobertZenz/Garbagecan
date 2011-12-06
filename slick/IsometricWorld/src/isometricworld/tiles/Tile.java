/*
 * Public Domain.
 */
package isometricworld.tiles;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public abstract class Tile {

	protected Image image;
	protected Shape innerArea;

	protected Tile(int width, int height) throws SlickException {
		this.image = new Image(width, height);
		this.innerArea = new Polygon(new float[]{
					width / 2, 0,
					width, height / 2,
					width / 2, height,
					0, height / 2
				});
	}

	public abstract void redraw() throws SlickException;

	public Image getImage() {
		return image;
	}

	public abstract Shape getHighlightShape();
}
