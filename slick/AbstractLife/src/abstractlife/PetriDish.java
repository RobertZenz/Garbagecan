/*
 * Public Domain
 */
package abstractlife;

import il.ac.idc.jdt.DelaunayTriangulation;
import il.ac.idc.jdt.Point;
import il.ac.idc.jdt.Triangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class PetriDish {

	private List<Cell> cells = new ArrayList<Cell>();

	public List<Cell> getCells() {
		return cells;
	}

	public Cell getCellAt(int x, int y) {
		Point at = new Point(x, y);

		for (Cell cell : cells) {
			if (cell.contains(at)) {
				return cell;
			}
		}

		return null;
	}

	public void init(long seed, int pointsCount, int width, int height) {
		Random random = new Random(seed);

		List<Point> points = new ArrayList<Point>();

		// Generate points
		for (int counter = 0; counter < pointsCount; counter++) {
			points.add(new Point(random.nextInt(width), random.nextInt(height)));
		}

		// Generate triangles
		List<Triangle> triangles = new DelaunayTriangulation(points).getTriangulation();

		// Generate cells
		for (Triangle triangle : triangles) {
			if (triangle.getA() != null && triangle.getB() != null && triangle.getC() != null) {
				cells.add(new Cell(triangle));
			}
		}

		// Get neighbors
		for (int idx = 0; idx < cells.size(); idx++) {
			Cell parent = cells.get(idx);
			for (int secIdx = idx + 1; secIdx < cells.size(); secIdx++) {
				Cell secondary = cells.get(secIdx);

				int matchingCorners = 0;
				matchingCorners += parent.isCorner(secondary.getA()) ? 1 : 0;
				matchingCorners += parent.isCorner(secondary.getB()) ? 1 : 0;
				matchingCorners += parent.isCorner(secondary.getC()) ? 1 : 0;

				switch (matchingCorners) {
					case 1:
						parent.getIndirectNeighbors().add(secondary);
						secondary.getIndirectNeighbors().add(parent);
						break;

					case 2:
						parent.getDirectNeighbors().add(secondary);
						secondary.getDirectNeighbors().add(parent);
						break;

					case 3:
						// Wait...what?!
						break;
				}
			}
		}
	}

	public void runGeneration() {
	}
}
