/*
 * Public Domain
 */
package net;

import il.ac.idc.jdt.DelaunayTriangulation;
import il.ac.idc.jdt.Point;
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
	private DelaunayTriangulation delaunay;
	private float maximumVelocity = 0.5f;
	private float minimumDistance = 100;
	private int pointCount = 200;
	private List<Point> points = new ArrayList<Point>();
	private Random random = new Random(0);
	private float speed = 0.2f;
	private List<Vector2f> velocities = new ArrayList<Vector2f>();

	public World(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		delaunay = new DelaunayTriangulation(points);

		for (int counter = 0; counter < pointCount; counter++) {
			points.add(new Point(random.nextFloat() * container.getWidth(),
					random.nextFloat() * container.getHeight()));
			velocities.add(new Vector2f(random.nextFloat() - 0.5f, random.nextFloat() - 0.5f));
		}
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		for (int idx = 0; idx < pointCount; idx++) {
			Point point = points.get(idx);
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

			point.setX(point.getX() + velocity.getX());
			point.setY(point.getY() + velocity.getY());

			if (point.getX() < border) {
				point.setX(container.getWidth() - border);
			}
			if (point.getX() > container.getWidth() - border) {
				point.setX(border);
			}
			if (point.getY() < border) {
				point.setY(container.getHeight() - border);
			}
			if (point.getY() > container.getHeight() - border) {
				point.setY(border);
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
				Point pointA = points.get(i);
				Point pointB = points.get(j);
				double distance = pointA.distance(pointB);
				if (distance <= minimumDistance) {
					color.a = (float)(1 - distance / minimumDistance);
					color.r = (float)(distance / minimumDistance);
					color.g = 1 - color.r;
					g.setColor(color);
					g.drawLine((float)pointA.getX(), (float)pointA.getY(),
							(float)pointB.getX(), (float)pointB.getY());
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
