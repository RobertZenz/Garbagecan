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
public class Threshold implements Calculator {

	public int calcNextValue(Iterable<Point> neighbors, int originalValue) {
		int highest = 0;
		int lowest = 255;

		for (Point neighbor : neighbors) {
			highest = Math.max(highest, neighbor.getValue());
			lowest = Math.min(lowest, neighbor.getValue());
		}

		if (originalValue > highest) {
			// Stay as you are, you're perfect!
			return originalValue;
		} else if (highest - originalValue > originalValue - lowest) {
			return lowest;
		} else {
			return highest;
		}
	}
}
