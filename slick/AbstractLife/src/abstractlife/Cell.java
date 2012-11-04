/*
 * Public Domain
 */
package abstractlife;

import il.ac.idc.jdt.Point;
import il.ac.idc.jdt.Triangle;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Cell extends Triangle {

	private boolean living;
	private boolean nextGenLiving;
	private List<Cell> directNeighbors = new ArrayList<Cell>();
	private List<Cell> indirectNeighbors = new ArrayList<Cell>();

	public List<Cell> getDirectNeighbors() {
		return directNeighbors;
	}

	public List<Cell> getIndirectNeighbors() {
		return indirectNeighbors;
	}

	public boolean isLiving() {
		return living;
	}

	public void setLiving(boolean living) {
		this.living = living;
	}

	public boolean isNextGenLiving() {
		return nextGenLiving;
	}

	public void setNextGenLiving(boolean nextGenLiving) {
		this.nextGenLiving = nextGenLiving;
	}

	public Cell(Point a, Point b, Point c) {
		super(a, b, c);
	}

	public Cell(Triangle triangle) {
		super(triangle.getA(), triangle.getB(), triangle.getC());
	}

	public void flushNextGeneration() {
		living = nextGenLiving;
	}
}
