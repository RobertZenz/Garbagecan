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
	private boolean isBorderCell;
	private List<Cell> neighbors;

	public Cell(PetriDish parent, int x, int y, boolean isBorderCell) {
		this.parent = parent;
		this.x = x;
		this.y = y;

		this.isBorderCell = isBorderCell;
		this.neighbors = new ArrayList<Cell>();
	}

	public boolean getValue() {
		return value;
	}

	public void setValue(boolean value) {
		if (!isBorderCell) {
			this.value = value;
		}
	}

	public void prepare() {
		if (!isBorderCell) {
			for (int neighborX = x - 1; neighborX <= x + 1; neighborX++) {
				for (int neighborY = y - 1; neighborY <= y + 1; neighborY++) {
					if (neighborX != x || neighborY != y) {
						neighbors.add(parent.getCell(neighborX, neighborY));
					}
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
