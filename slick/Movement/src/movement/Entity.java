/*
 * Public Domain
 */
package movement;

import java.io.File;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Entity implements Drawable {

	private boolean printDebug = false;

	public boolean getPrintDebug() {
		return printDebug;
	}

	public void setPrintDebug(boolean enable) {
		printDebug = enable;
	}

	@Override
	public String toString() {
		return "X: " + x + " Y: " + y;
	}

	public void draw(Graphics grphcs) {
		grphcs.drawImage(image, x, y);
		if (printDebug) {
			grphcs.drawString(toString(), x, y + image.getHeight());
		}
	}
	private Image image;

	public Image getImage() {
		return image;
	}
	private float x;
	private float y;

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Entity(String resourceFolder) throws SlickException {
		this.image = new Image(new File(resourceFolder, "snail.png").toString());
	}
}
