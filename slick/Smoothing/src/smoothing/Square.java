/*
 * Public Domain
 */
package smoothing;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

public class Square {

	public static boolean triangleSmooth = true;
	private int x;
	private int y;
	/*
	 * 0 1 2
	 * 3 X 5
	 * 6 7 8
	 */
	private Square[] neighbors = new Square[9];
	private boolean flipped;

	public Square[] getNeighbors() {
		return neighbors;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isFlipped() {
		return flipped;
	}

	public void setNeighbors(Square[] neighbors) {
		this.neighbors = neighbors;
	}

	public void setFlipped(boolean flipped) {
		this.flipped = flipped;
	}

	public Square(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Square(int x, int y, Square[] neighbors) {
		this.x = x;
		this.y = y;
		this.neighbors = neighbors;
	}

	public void draw(Graphics g, int left, int top, int size) {
		int half = size / 2;

		boolean topPresent = neighbors[1] != null && neighbors[1].isFlipped();
		boolean leftPresent = neighbors[3] != null && neighbors[3].isFlipped();
		boolean rightPresent = neighbors[5] != null && neighbors[5].isFlipped();
		boolean bottomPresent = neighbors[7] != null && neighbors[7].isFlipped();

		if ((!topPresent && !leftPresent && !rightPresent && !bottomPresent)
				|| (topPresent && bottomPresent) || (leftPresent && rightPresent)) {
			g.fillRect(left, top, size, size);
			return;
		}


		if (triangleSmooth) {
			if (topPresent) {
				g.fill(new Polygon(new float[]{left, top, left + size, top, left + half, top + half}));
			}
			if (leftPresent) {
				g.fill(new Polygon(new float[]{left, top, left, top + size, left + half, top + half}));
			}
			if (rightPresent) {
				g.fill(new Polygon(new float[]{left + size, top, left + size, top + size, left + half, top + half}));
			}
			if (bottomPresent) {
				g.fill(new Polygon(new float[]{left, top + size, left + size, top + size, left + half, top + half}));
			}
		} else {
			if (topPresent) {
				g.fillRect(left, top, size, half);
			}
			if (leftPresent) {
				g.fillRect(left, top, half, size);
			}
			if (rightPresent) {
				g.fillRect(left + half, top, half, size);
			}
			if (bottomPresent) {
				g.fillRect(left, top + half, size, half);
			}
		}
	}
}
