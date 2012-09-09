/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mufugenerator.pipeline;

import il.ac.idc.jdt.Point;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.geom.Polygon;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class PolygonGenerator {

	public static Polygon[] makePolygons(Point[] points, int maxDistance) {
		List<Polygon> polygons = new ArrayList<Polygon>();

		for (int idx = 0; idx < points.length; idx++) {
			Point pointA = points[idx];

			float pointBDistance = Float.MAX_VALUE;
			Point pointB = null;
			float pointCDistance = Float.MAX_VALUE;
			Point pointC = null;

			for (int idx2 = idx + 1; idx2 < points.length; idx2++) {
				Point point = points[idx2];
				float distanceX = (float)Math.abs(pointA.getX() - point.getX());
				float distanceY = (float)Math.abs(pointA.getY() - point.getY());

				float distance = (float) Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
				if (distance <= maxDistance) {
					if (distance < pointBDistance) {
						pointBDistance = distance;
						pointB = point;
					} else if (distance < pointCDistance) {
						pointCDistance = distance;
						pointC = point;
					}
				}
			}

			if (pointB != null && pointC != null) {
				Polygon polygon = new Polygon();
				polygon.addPoint((float)pointA.getX(), (float)pointA.getY());
				polygon.addPoint((float)pointB.getX(), (float)pointB.getY());
				polygon.addPoint((float)pointC.getX(), (float)pointC.getY());
				polygons.add(polygon);
			}
		}

		return polygons.toArray(new Polygon[polygons.size() - 1]);
	}
}
