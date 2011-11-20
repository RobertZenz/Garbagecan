/*
 * Public Domain
 */
package movement;

import org.newdawn.slick.Graphics;

public interface Drawable {

	public void draw(Graphics grphcs);

	public void setPrintDebug(boolean enable);

	public boolean getPrintDebug();
}
