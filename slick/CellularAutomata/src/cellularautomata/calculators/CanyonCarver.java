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
public class CanyonCarver implements Calculator {

	public int calcNextValue(Iterable<Point> neighbors, int originalValue) {
		int countHigher = 0;
		int countLower = 0;

		for (Point neighbor : neighbors) {
			if (neighbor.getValue() > originalValue) {
				countHigher++;
			} else {
				countLower++;
			}
		}

		if (countHigher > countLower) {
			return originalValue - 1;
		} else {
			return originalValue;
		}
	}
}
