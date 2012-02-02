/*
 * Public Domain
 */
package abugsway;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Bug {

	private static final float SPEED = 0.2f;
	private static final int SIZE = 20;
	private static final Color COLOR = Color.red;
	private float x;
	private float y;
	private int targetX;
	private int targetY;

	public int getTargetX() {
		return targetX;
	}

	public int getTargetY() {
		return targetY;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public boolean isOnTarget() {
		return x == targetX && y == targetY;
	}

	public boolean isOnTarget(int x, int y) {
		return this.x == x && this.y == y;
	}

	public void setTarget(int x, int y) {
		targetX = x;
		targetY = y;
	}

	public void update(int delta) {
		Vector2f move = new Vector2f(targetX - x, targetY - y);
		move = move.normalise();

		float distanceX = x - targetX;
		float distanceY = y - targetY;

		float deltaSpeed = SPEED * delta;

		if (Math.abs(distanceX) > deltaSpeed) {
			x += move.x * deltaSpeed;
		} else {
			x = targetX;
		}
		if (Math.abs(distanceY) > deltaSpeed) {
			y += move.y * deltaSpeed;
		} else {
			y = targetY;
		}
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		g.setColor(COLOR);
		g.fillOval(x - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
	}
}
