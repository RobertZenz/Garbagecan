/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mufugenerator.pipeline;

import il.ac.idc.jdt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class PointGenerator {

	public static Point[] generatePoints(long seed, int amount, int width, int height) {
		List<Point> points = new ArrayList<Point>();

		Random rand = new Random(seed);
		for (int counter = 0; counter < amount; counter++) {
			points.add(new Point(rand.nextInt(width), rand.nextInt(height)));
		}

		return points.toArray(new Point[amount - 1]);
	}
}
