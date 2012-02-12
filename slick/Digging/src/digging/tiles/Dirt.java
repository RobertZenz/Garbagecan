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
public class Dirt extends Tile {

	private static final Color BROWN = new Color(128, 128, 64);

	@Override
	public float getDepth() {
		return 0;
	}

	@Override
	public boolean isDiggable() {
		return true;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
	}

	@Override
	public void render(GameContainer container, Graphics g, Rectangle area) throws SlickException {
		g.setColor(BROWN);
		g.fillRect(area.getX(), area.getY(), area.getWidth(), area.getHeight());

		g.setColor(BROWN.darker());
		g.drawRoundRect(area.getX(), area.getY(), area.getWidth(), area.getHeight(), 12);
	}
}
