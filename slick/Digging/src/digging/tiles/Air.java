/*
 * Public Domain
 */
package digging.tiles;

import digging.Tile;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Air extends Tile {

	@Override
	public float getDepth() {
		return Float.MAX_VALUE;
	}

	@Override
	public boolean isDiggable() {
		return false;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		/// Nothing by now
	}

	@Override
	public void render(GameContainer container, Graphics g, Rectangle area) throws SlickException {
		g.setColor(Color.cyan);
		g.fillRect(area.getX(), area.getY(), area.getWidth(), area.getHeight());

		g.setColor(Color.cyan.darker());
		g.drawRoundRect(area.getX(), area.getY(), area.getWidth(), area.getHeight(), 12);
	}
}
