/*
 * Public Domain
 */
package digging;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public abstract class Tile {

	public static final float SIZE = 128;

	public abstract float getDepth();

	public abstract boolean isDiggable();

	public abstract void update(GameContainer container, int delta) throws SlickException;

	public abstract void render(GameContainer container, Graphics g, Rectangle area) throws SlickException;
}
