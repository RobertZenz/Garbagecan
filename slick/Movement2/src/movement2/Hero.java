/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package movement2;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author robert
 */
public class Hero {

    private float x;
    private float y;
    private float width = 15;
    private float height = 15;
    
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

    public float getHeight() {
	return height;
    }

    public float getWidth() {
	return width;
    }

    public void move(float modX, float modY, int screenWidth, int screenHeight) {
	x += modX;
	y += modY;

	if (x < 0) {
	    x = 0;
	} else if (x > screenWidth-width) {
	    x = screenWidth-width;
	}
	if (y < 0) {
	    y = 0;
	} else if (y > screenHeight-height) {
	    y = screenHeight-height;
	}
    }

    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
	grphcs.drawOval(x, y, width, height);

	grphcs.drawString(Float.toString(x), x, y + 25);
	grphcs.drawString(Float.toString(y), x, y + 40);
    }
}
