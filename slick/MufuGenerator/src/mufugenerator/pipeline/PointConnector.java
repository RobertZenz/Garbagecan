/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mufugenerator.pipeline;

import il.ac.idc.jdt.Point;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.geom.Line;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class PointConnector {

	public static Line[] makeConnections(Point[] points, int maxDistance) {
		List<Line> lines = new ArrayList<Line>();

		for (int idx = 0; idx < points.length; idx++) {
			Point pointA = points[idx];
			for (int idx2 = idx + 1; idx2 < points.length; idx2++) {
				Point pointB = points[idx2];
				float distanceX = (float) Math.abs(pointA.getX() - pointB.getX());
				float distanceY = (float) Math.abs(pointA.getY() - pointB.getY());

				float distance = (float) Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
				if (distance <= maxDistance) {
					lines.add(new Line((float)pointA.getX(), (float)pointA.getY(), (float)pointB.getX(), (float)pointB.getY()));
				}
			}
		}

		return lines.toArray(new Line[lines.size() - 1]);
	}
}
