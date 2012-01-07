/*
 * Public Domain
 */
package movement3;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Player {

	private int height;
	private int location;

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public Player(int height) {
		this.height = height;
	}

	public void render(Graphics g, int terrainHeight) {
		g.setColor(Color.red);
		g.drawLine(location, terrainHeight - height, location, terrainHeight);
	}
}
