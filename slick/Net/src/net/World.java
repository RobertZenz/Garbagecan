/*
 * Public Domain
 */
package net;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class World extends BasicGame {

	private int border = 20;
	private float maximumVelocity = 0.5f;
	private float minimumDistance = 100;
	private int pointCount = 200;
	private List<Vector2f> points = new ArrayList<Vector2f>();
	private Random random = new Random(0);
	private float speed = 0.2f;
	private List<Vector2f> velocities = new ArrayList<Vector2f>();

	public World(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		for (int counter = 0; counter < pointCount; counter++) {
			points.add(new Vector2f(random.nextFloat() * container.getWidth(),
					random.nextFloat() * container.getHeight()));
			velocities.add(new Vector2f(random.nextFloat() - 0.5f, random.nextFloat() - 0.5f));
		}
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		for (int idx = 0; idx < pointCount; idx++) {
			Vector2f point = points.get(idx);
			Vector2f velocity = velocities.get(idx);

			velocity.x += (random.nextFloat() - 0.5) * speed;
			velocity.y += (random.nextFloat() - 0.5) * speed;

			if (velocity.x > maximumVelocity) {
				velocity.x = maximumVelocity;
			}
			if (velocity.x < -maximumVelocity) {
				velocity.x = -maximumVelocity;
			}
			if (velocity.y > maximumVelocity) {
				velocity.y = maximumVelocity;
			}
			if (velocity.y < -maximumVelocity) {
				velocity.y = -maximumVelocity;
			}

			point.x += velocity.x;
			point.y += velocity.y;

			if (point.x < border) {
				point.x = container.getWidth() - border;
			}
			if (point.x > container.getWidth() - border) {
				point.x = border;
			}
			if (point.y < border) {
				point.y = container.getHeight() - border;
			}
			if (point.y > container.getHeight() - border) {
				point.y = border;
			}
		}

		Input input = container.getInput();

		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		g.setBackground(Color.black);

		Color color = new Color(1f, 1f, 1f);

		for (int i = 0; i < pointCount; i++) {
			for (int j = i + 1; j < pointCount; j++) {
				Vector2f pointA = points.get(i);
				Vector2f pointB = points.get(j);
				float distance = pointA.distance(pointB);
				if (distance <= minimumDistance) {
					color.a = 1 - distance / minimumDistance;
					color.r = distance / minimumDistance;
					color.g = 1 - color.r;
					g.setColor(color);
					g.drawLine(pointA.x, pointA.y, pointB.x, pointB.y);
				}
			}
		}
	}

	public float getMinimumDistance() {
		return minimumDistance;
	}

	public void setMinimumDistance(float minimumDistance) {
		this.minimumDistance = minimumDistance;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
