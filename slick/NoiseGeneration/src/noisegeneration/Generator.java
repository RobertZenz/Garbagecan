/*
 * Public Domain
 */
package noisegeneration;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author robert
 */
public interface Generator {

	boolean getDrawDots();

	void setDrawDots(boolean drawDots);

	void generate(long seed, int width, int resolution) throws SlickException;

	void render(GameContainer gc, Graphics grphcs) throws SlickException;
}
