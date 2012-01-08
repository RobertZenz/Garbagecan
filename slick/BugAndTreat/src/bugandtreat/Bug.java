/*
 * Public Domain
 */
package bugandtreat;

import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Bug {

	private Color color;
	private Random random;
	private World world;
	private float x;
	private float y;
	private float size = 10;
	private float growth = 0.3f;
	private float speed = 1f;
	private float viewDistance = 150;
	private Vector2f movement = new Vector2f(0, 0);
	private Treat targetTreat;

	public Bug(long seed, World world, Color color, float viewDistance) {
		this.random = new Random(seed);
		this.world = world;
		this.color = color;
		this.viewDistance = viewDistance;

		this.x = random.nextInt(world.getWidth());
		this.y = random.nextInt(world.getHeight());
	}

	public Vector2f getMovement() {
		return movement;
	}

	public float getSize() {
		return size;
	}

	public Treat getTargetTreat() {
		return targetTreat;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillOval(x - size / 2, y - size / 2, size, size);

		int arcPart = 360 / 20;
		for (int arc = 0; arc <= 360; arc += arcPart) {
			g.drawArc(x - viewDistance, y - viewDistance, viewDistance*2, viewDistance*2, arc, arc + arcPart/2);
		}

		if (size > 100) {
			g.setColor(Color.black);
			g.fillOval(x - size / 3, y - size / 3, size / 4, size / 4);
			g.fillOval(x + size / 6, y - size / 3, size / 4, size / 4);
			g.fillOval(x - size / 4, y, size / 2, size / 2);
		}
	}

	public void update() {
		float distanceToTreat = Float.MAX_VALUE;
		if (targetTreat != null && world.treatStillExists(targetTreat)) {
			movement.set(targetTreat.getX() - x, targetTreat.getY() - y);
			distanceToTreat = (float) Math.sqrt(Math.pow(Math.abs(movement.getX()), 2) + Math.pow(Math.abs(movement.getY()), 2));

			if (distanceToTreat < size / 2) {
				world.removeTreat(targetTreat);
				targetTreat = null;
				distanceToTreat = Float.MAX_VALUE;
				size += growth;
			}
		} else {
			targetTreat = null;
			if (random.nextFloat() > 0.99f) {
				movement.set(random.nextFloat() - 0.5f, random.nextFloat() - 0.5f);
			}
		}

		// Check the world for new treats
		for (Treat treat : world.getTreats()) {
			Vector2f newMovement = new Vector2f(treat.getX() - x, treat.getY() - y);
			float distance = (float) Math.sqrt(Math.pow(Math.abs(newMovement.getX()), 2) + Math.pow(Math.abs(newMovement.getY()), 2));
			if (distance < viewDistance && distance < distanceToTreat) {
				targetTreat = treat;
				movement = newMovement;
				distanceToTreat = distance;
			}
		}

		movement = movement.normalise();

		x += movement.getX() * speed;
		y += movement.getY() * speed;

		// Validate coordinates
		x = Math.max(0, Math.min(world.getWidth(), x));
		y = Math.max(0, Math.min(world.getHeight(), y));
	}
}