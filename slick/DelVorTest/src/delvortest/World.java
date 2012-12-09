/*
 * Public Domain
 */
package delvortest;

import il.ac.idc.jdt.DelaunayTriangulation;
import il.ac.idc.jdt.Point;
import il.ac.idc.jdt.Triangle;
import java.util.ArrayList;
import java.util.List;
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

	private static int POINT_SIZE = 5;
	private List<Vector2f> points;
	private DelaunayTriangulation delaunay;
	private List<Point[]> voronoi;
	private boolean drawPoints = true;
	private boolean drawDelaunay = true;
	private boolean drawVoronoi = true;

	public World(String name) {
		super(name);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		points = new ArrayList<Vector2f>();
		delaunay = new DelaunayTriangulation();
		voronoi = new ArrayList<Point[]>();
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();

		if (input.isMousePressed(0)) {
			int x = input.getMouseX();
			int y = input.getMouseY();

			addPoint(x, y);
			calcVoronoi();
		}

		if (input.isKeyPressed(Input.KEY_1)) {
			drawPoints = !drawPoints;
		}
		if (input.isKeyPressed(Input.KEY_2)) {
			drawDelaunay = !drawDelaunay;
		}
		if (input.isKeyPressed(Input.KEY_3)) {
			drawVoronoi = !drawVoronoi;
		}

		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			container.exit();
		}
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		g.setBackground(Color.white);

		if (drawDelaunay) {
			g.setColor(Color.blue);
			for (Triangle triangle : delaunay.getTriangulation()) {
				drawLine(g, triangle.getA(), triangle.getB());
				if (!triangle.isHalfplane()) {
					drawLine(g, triangle.getB(), triangle.getC());
					drawLine(g, triangle.getC(), triangle.getA());
				}
			}
		}

		if (drawVoronoi) {
			g.setColor(Color.red);
			for (Point[] vorPoints : voronoi) {
				if (vorPoints.length >= 2) {
					for (int idx = 0; idx < vorPoints.length - 1; idx++) {
						drawLine(g, vorPoints[idx], vorPoints[idx + 1]);
					}
					drawLine(g, vorPoints[vorPoints.length - 1], vorPoints[0]);
				}
			}
		}

		if (drawPoints) {
			g.setColor(Color.black);
			for (Vector2f point : points) {
				g.drawOval(point.getX() - (POINT_SIZE / 2), point.getY() - (POINT_SIZE / 2), POINT_SIZE, POINT_SIZE);
			}
		}
	}

	private void addPoint(int x, int y) {
		points.add(new Vector2f(x, y));
		delaunay.insertPoint(new Point(x, y));
	}

	private void calcVoronoi() {
		voronoi.clear();
		for (Triangle triangle : delaunay.getTriangulation()) {
			voronoi.add(delaunay.calcVoronoiCell(triangle, triangle.getA()));
		}
	}

	private void drawLine(Graphics g, Point a, Point b) {
		g.drawLine((float) a.getX(), (float) a.getY(), (float) b.getX(), (float) b.getY());
	}
}
