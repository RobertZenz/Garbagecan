/*
 * Public Domain
 */
package cellularautomata;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Point {

	private Calculator calculator;
	private List<Point> neighbors;
	private int newValue;
	private int value;

	public Point() {
		neighbors = new ArrayList<Point>();
	}

	public void calcNextState() {
		newValue = calculator.calcNextValue(neighbors, value);
	}

	public void flush() {
		value = Math.min(Math.max(newValue, 0), 255);
	}

	public int getValue() {
		return value;
	}

	public void init(Map map, int x, int y) {
		neighbors.clear();

		int startX = Math.max(0, x - 1);
		int endX = Math.min(map.getWidth(), x + 2);
		int startY = Math.max(0, y - 1);
		int endY = Math.min(map.getHeight(), y + 2);

		for (int idxX = startX; idxX < endX; idxX++) {
			for (int idxY = startY; idxY < endY; idxY++) {
				if (idxX != x || idxY != y) {
					neighbors.add(map.getPoint(idxX, idxY));
				}
			}
		}
	}

	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
