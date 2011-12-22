/*
 * Public Domain
 */
package cellularautomata.calculators;

import cellularautomata.Calculator;
import cellularautomata.Point;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Flatten implements Calculator {

	public int calcNextValue(Iterable<Point> neighbors, int originalValue) {
		int sum = 0;
		int count = 0;

		for (Point neighbor : neighbors) {
			sum += neighbor.getValue();
			count++;
		}

		return sum / count;
	}
}
