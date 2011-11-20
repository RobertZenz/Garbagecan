/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package movement2;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author robert
 */
public class World {

    private Image bg;
    private float x;
    private float y;
    private Hero hero;

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

    public Hero getHero() {
	return hero;
    }

    public World() throws SlickException {
	this.bg = new Image("data/bg.png");

	this.hero = new Hero();
    }

    public void move(float modX, float modY, int screenWidth, int screenHeight) {
	// Check if the world is at the left edge and
	// if the hero is moving in the left half.
	if (x >= 0 && hero.getX() <= screenWidth / 2) {
	    x = 0;
	    hero.move(modX, modY, screenWidth, screenHeight);
	} else if (x <= (screenWidth - bg.getWidth()) && hero.getX() >= screenWidth / 2) {
	    x = screenWidth - bg.getWidth();
	    hero.move(modX, modY, screenWidth, screenHeight);
	} else {
	    x -= modX; // Invert movement for the world.
	    hero.setX(screenWidth / 2);
	}

	if (y >= 0 && hero.getY() <= screenHeight / 2) {
	    y = 0;
	    hero.move(modX, modY, screenWidth, screenHeight);
	} else if (y <= (screenHeight - bg.getHeight()) && hero.getY() >= screenHeight / 2) {
	    y = screenHeight - bg.getHeight();
	    hero.move(modX, modY, screenWidth, screenHeight);
	} else {
	    y -= modY; // Invert
	    hero.setY(screenHeight / 2);
	}
    }

    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
	grphcs.drawImage(bg, x, y);

	hero.render(gc, grphcs);
    }
}
