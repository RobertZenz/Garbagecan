/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mufugenerator;

import il.ac.idc.jdt.DelaunayTriangulation;
import il.ac.idc.jdt.Point;
import il.ac.idc.jdt.Triangle;
import java.util.ArrayList;
import java.util.List;
import mufugenerator.pipeline.PointGenerator;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Polygon;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class World extends BasicGame {

	final int maxDistance = 100;
	final int maxCircleRadius = 2000;
	final int pointCount = 200;
	Point[] points;
	Line[] lines;
	Polygon[] polygons;
	List<Triangle> triangles;
	List<Point> centers;
	List<Triangle> smallTriangles;

	public World(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		points = PointGenerator.generatePoints(0, pointCount, container.getWidth(), container.getHeight());
		//lines = PointConnector.makeConnections(points, maxDistance);
		//polygons = PolygonGenerator.makePolygons(points, maxDistance);

		DelaunayTriangulation delaunay = new DelaunayTriangulation(points);
		triangles = delaunay.getTriangulation();

//		centers = new ArrayList<Point>();
//		for (Triangle triangle : triangles) {
//			Circle circle = triangle.getCircumCircle();
//			if (circle != null) {
//				centers.add(circle.center());
//			}
//		}

		smallTriangles = new ArrayList<Triangle>();
		for (Triangle triangle : triangles) {
			if (triangle.getA() != null && triangle.getB() != null && triangle.getC() != null) {
				if (triangle.getCircumCircle().radius() < 2000) {
					smallTriangles.add(triangle);
				}
			}
		}
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		g.setBackground(Color.white);

//		g.setColor(Color.pink);
//		for (Point point : points) {
//			g.drawOval((float) (point.getX() - maxDistance), (float) (point.getY() - maxDistance), maxDistance * 2, maxDistance * 2);
//		}
//
//		g.setColor(Color.red);
//		for (Point point : points) {
//			g.drawOval((float) (point.getX() - 1), (float) (point.getY() - 1), 3, 3);
//		}
//
//		if (lines != null) {
//			g.setColor(Color.green);
//			for (Line line : lines) {
//				g.drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
//			}
//		}
//
//		if (polygons != null) {
//			g.setColor(Color.cyan);
//			for (Polygon polygon : polygons) {
//				g.draw(polygon);
//			}
//		}

//		if (triangles != null) {
//			g.setColor(Color.blue);
//			for (Triangle triangle : triangles) {
//				if (triangle.getA() != null && triangle.getB() != null && triangle.getC() != null) {
//					g.drawLine((float) triangle.getA().getX(), (float) triangle.getA().getY(), (float) triangle.getB().getX(), (float) triangle.getB().getY());
//					g.drawLine((float) triangle.getB().getX(), (float) triangle.getB().getY(), (float) triangle.getC().getX(), (float) triangle.getC().getY());
//					g.drawLine((float) triangle.getC().getX(), (float) triangle.getC().getY(), (float) triangle.getA().getX(), (float) triangle.getA().getY());
//				}
//			}
//			g.setColor(Color.green);
//			for (Triangle triangle : triangles) {
//				if (triangle.getA() != null && triangle.getB() != null && triangle.getC() != null) {
//					if (triangle.getCircumCircle().radius() <= maxCircleRadius) {
//						g.drawLine((float) triangle.getA().getX(), (float) triangle.getA().getY(), (float) triangle.getB().getX(), (float) triangle.getB().getY());
//						g.drawLine((float) triangle.getB().getX(), (float) triangle.getB().getY(), (float) triangle.getC().getX(), (float) triangle.getC().getY());
//						g.drawLine((float) triangle.getC().getX(), (float) triangle.getC().getY(), (float) triangle.getA().getX(), (float) triangle.getA().getY());
//					}
//				}
//			}
//		}
//
//		if (centers != null) {
//			g.setColor(Color.darkGray);
//			for (Point point : centers) {
//				g.drawOval((float) (point.getX() - 1), (float) (point.getY() - 1), 3, 3);
//			}
//		}

//		if (smallTriangles != null) {
//			g.setColor(Color.blue);
//			for (Triangle triangle : smallTriangles) {
//				drawTriangle(g, triangle);
//			}
//		}

		if (smallTriangles != null) {
			g.setBackground(Color.blue);
			g.setColor(Color.green);
			for (Triangle triangle : smallTriangles) {
				fillTriangle(g, triangle);
			}
			g.setColor(Color.red);
			for (Triangle triangle : smallTriangles) {
				drawTriangle(g, triangle);
			}
		}
	}

	private static void drawTriangle(Graphics g, Triangle triangle) {
		g.drawLine((float) triangle.getA().getX(), (float) triangle.getA().getY(), (float) triangle.getB().getX(), (float) triangle.getB().getY());
		g.drawLine((float) triangle.getB().getX(), (float) triangle.getB().getY(), (float) triangle.getC().getX(), (float) triangle.getC().getY());
		g.drawLine((float) triangle.getC().getX(), (float) triangle.getC().getY(), (float) triangle.getA().getX(), (float) triangle.getA().getY());
	}

	private static void fillTriangle(Graphics g, Triangle triangle) {
		Polygon polygon = new Polygon(new float[]{
					(float) triangle.getA().getX(),
					(float) triangle.getA().getY(),
					(float) triangle.getB().getX(),
					(float) triangle.getB().getY(),
					(float) triangle.getC().getX(),
					(float) triangle.getC().getY()
				});
		polygon.setClosed(true);

		g.fill(polygon);
	}
}
