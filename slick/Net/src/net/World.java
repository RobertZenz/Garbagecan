/*
 * Public Domain
 */
package net;

import il.ac.idc.jdt.DelaunayTriangulation;
import il.ac.idc.jdt.Point;
import il.ac.idc.jdt.Triangle;
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
	private List<Point> points = new ArrayList<Point>();
	private Random random = new Random(0);
	private float speed = 0.2f;
	private List<Vector2f> velocities = new ArrayList<Vector2f>();

	public World(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		for (int counter = 0; counter < pointCount; counter++) {
			Point point = new Point(random.nextFloat() * container.getWidth(),
					random.nextFloat() * container.getHeight());
			points.add(point);
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

		renderVoronoi(g);
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

	private void renderMishMash(Graphics g) {
		for (int i = 0; i < pointCount; i++) {
			for (int j = i + 1; j < pointCount; j++) {
				checkAndDraw(g, points.get(i), points.get(j));
			}
		}
	}

	private void renderDelaunay(Graphics g) {
		DelaunayTriangulation delaunay = new DelaunayTriangulation(points);
		for (Triangle triangle : delaunay.getTriangulation()) {
			checkAndDraw(g, triangle.getA(), triangle.getB());
			checkAndDraw(g, triangle.getB(), triangle.getC());
			checkAndDraw(g, triangle.getC(), triangle.getA());
		}
	}

	private void renderVoronoi(Graphics g) {
		DelaunayTriangulation delaunay = new DelaunayTriangulation(points);
		for (Triangle triangle : delaunay.getTriangulation()) {
			Point[] vorPoints = delaunay.calcVoronoiCell(triangle, triangle.getA());
			if (vorPoints.length >= 2) {
				for (int idx = 0; idx < vorPoints.length - 1; idx++) {
					checkAndDraw(g, vorPoints[idx], vorPoints[idx + 1]);
				}
				checkAndDraw(g, vorPoints[vorPoints.length - 1], vorPoints[0]);
			}
		}
	}

	private void checkAndDraw(Graphics g, Point a, Point b) {
		if (a == null || b == null) {
			return;
		}

		double distance = a.distance(b);

		if (distance <= minimumDistance) {
			Color color = new Color(1f, 1f, 1f);
			color.a = (float) (1 - distance / minimumDistance);
			color.r = (float) (distance / minimumDistance);
			color.g = 1 - color.r;
			g.setColor(color);
			g.drawLine((float) a.getX(), (float) a.getY(),
					(float) b.getX(), (float) b.getY());
		}
	}
}
