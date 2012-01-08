/*
 * Public Domain
 */
package bugandtreat;

import java.util.ArrayList;
import java.util.List;
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
	private List<Treat> targetTreats = new ArrayList<Treat>();

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

	public Iterable<Treat> getTargetTreats() {
		return targetTreats;
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

		Treat previousTreat = null;
		for (Treat treat : targetTreats) {
			if (previousTreat == null) {
				g.drawLine(x, y, treat.getX(), treat.getY());
			} else {
				g.drawLine(previousTreat.getX(), previousTreat.getY(), treat.getX(), treat.getY());
			}
			previousTreat = treat;
		}

		int arcPart = 360 / 20;
		for (int arc = 0; arc <= 360; arc += arcPart) {
			g.drawArc(x - viewDistance, y - viewDistance, viewDistance * 2, viewDistance * 2, arc, arc + arcPart / 2);
		}

		if (size > 100) {
			g.setColor(Color.black);
			g.fillOval(x - size / 3, y - size / 3, size / 4, size / 4);
			g.fillOval(x + size / 6, y - size / 3, size / 4, size / 4);
			g.fillOval(x - size / 4, y, size / 2, size / 2);
		}
	}

	public void update() {
		updateTreats();

		float distanceToTreat = Float.MAX_VALUE;
		if (!targetTreats.isEmpty() && world.treatStillExists(targetTreats.get(0))) {
			Treat targetTreat = targetTreats.get(0);
			movement.set(targetTreat.getX() - x, targetTreat.getY() - y);
			distanceToTreat = (float) Math.sqrt(Math.pow(Math.abs(movement.getX()), 2) + Math.pow(Math.abs(movement.getY()), 2));

			if (distanceToTreat < size / 2) {
				world.removeTreat(targetTreat);
				targetTreat = null;
				distanceToTreat = Float.MAX_VALUE;
				size += growth;
			}
		} else {
			if (random.nextFloat() > 0.99f) {
				movement.set(random.nextFloat() - 0.5f, random.nextFloat() - 0.5f);
			}
		}

		movement = movement.normalise();

		x += movement.getX() * speed;
		y += movement.getY() * speed;

		// Validate coordinates
		x = Math.max(0, Math.min(world.getWidth(), x));
		y = Math.max(0, Math.min(world.getHeight(), y));
	}

	private void updateTreats() {
		List<Treat> unsortedTreats = new ArrayList<Treat>();
		// Get
		for (Treat treat : world.getTreats()) {
			Vector2f newMovement = new Vector2f(treat.getX() - x, treat.getY() - y);
			if (getDistance(newMovement) < viewDistance) {
				unsortedTreats.add(treat);
			}
		}

		targetTreats.clear();

		// Sort
		while (!unsortedTreats.isEmpty()) {
			Treat previousTreat = null;
			float previousDistance = Float.MAX_VALUE;

			for (Treat treat : unsortedTreats) {
				Vector2f newMovement;
				if (targetTreats.isEmpty()) {
					newMovement = new Vector2f(treat.getX() - x, treat.getY() - y);
				} else {
					newMovement = new Vector2f(treat.getX() - targetTreats.get(targetTreats.size() - 1).getX(), treat.getY() - targetTreats.get(targetTreats.size() - 1).getY());
				}
				float newDistance = getDistance(newMovement);

				if (newDistance < previousDistance) {
					previousTreat = treat;
					previousDistance = newDistance;
				}
			}

			targetTreats.add(previousTreat);
			unsortedTreats.remove(previousTreat);
		}
	}

	private float getDistance(Vector2f vector) {
		return (float) Math.sqrt(Math.pow(Math.abs(vector.getX()), 2) + Math.pow(Math.abs(vector.getY()), 2));
	}
}
