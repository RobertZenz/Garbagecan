/*
 * Public Domain
 */
package gameoflife2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Cell {

	private PetriDish parent;
	private int x;
	private int y;
	private boolean value;
	private boolean nextValue;
	private List<Cell> neighbors = new ArrayList<Cell>();

	public Cell(PetriDish parent, int x, int y) {
		this.parent = parent;
		this.x = x;
		this.y = y;
	}

	public boolean getValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public void init() {
		int minX = Math.max(x - 1, 0);
		int maxX = Math.min(x + 1, parent.getWidth() - 1);
		int minY = Math.max(y - 1, 0);
		int maxY = Math.min(y + 1, parent.getHeight() - 1);

		for (int neighborX = minX; neighborX <= maxX; neighborX++) {
			for (int neighborY = minY; neighborY <= maxY; neighborY++) {
				// Check if it is not this
				if (neighborX != x || neighborY != y) {
					neighbors.add(parent.getCell(neighborX, neighborY));
				}
			}
		}
	}

	public void startEvolution() {
		nextValue = checkSurvival(value, countAliveNeighbors(neighbors));
	}

	public void finishEvolution() {
		value = nextValue;
	}

	private static int countAliveNeighbors(Iterable<Cell> neighbors) {
		int neighborsAlive = 0;

		for (Cell neighbor : neighbors) {
			if (neighbor.value) {
				neighborsAlive++;
			}
		}

		return neighborsAlive;
	}

	private static boolean checkSurvival(boolean isAlive, int neighborsAlive) {
		switch (neighborsAlive) {
			case 0:
			case 1:
				return false;

			case 2:
				return isAlive;

			case 3:
				return true;

			default:
				return false;
		}
	}
}
