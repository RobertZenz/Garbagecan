/*
 * Public Domain
 */
package digging;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public abstract class Entity {

	protected World world;
	protected float locationX;
	protected float locationY;

	public float getLocationX() {
		return locationX;
	}

	public float getLocationY() {
		return locationY;
	}

	public abstract void update(GameContainer container, int delta) throws SlickException;

	public abstract void render(GameContainer container, Graphics g) throws SlickException;
}
