/*
 * Public Domain
 */
package gameoflife2;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class PetriDish {

	private Cell[] cells;
	private int width;
	private int height;
	private long generation;
	private long duration;

	public PetriDish(int width, int height) {
		this.width = width;
		this.height = height;

		this.cells = new Cell[width * height];
	}

	public Cell[] getCells() {
		return cells;
	}

	public Cell getCell(int x, int y) {
		return cells[y * width + x];
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public long getGeneration() {
		return generation;
	}

	public long getDuration() {
		return duration;
	}

	public void init() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				cells[y * width + x] = new Cell(this, x, y);
			}
		}

		for (Cell cell : cells) {
			cell.init();
		}
	}

	public void doEvolution() {
		long start = System.currentTimeMillis();

		for (Cell cell : cells) {
			cell.startEvolution();
		}
		for (Cell cell : cells) {
			cell.finishEvolution();
		}

		generation++;
		duration = System.currentTimeMillis() - start;
	}
}
