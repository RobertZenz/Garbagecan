/*
 * Public Domain
 */
package cellularautomata;

import cellularautomata.calculators.CanyonCarver;
import java.util.Random;
import org.newdawn.slick.Color;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Map {

	private Point[] points;
	private int width;
	private int height;
	private Calculator calculator;
	private Color[] colorLookup;
		
	public Map(int width, int height) {
		this.width = width;
		this.height = height;

		this.points = new Point[width * height];

		colorLookup = new Color[256];
		for (int value = 0; value < 256; value++) {
			colorLookup[value] = new Color(value, value, value);
		}

		this.calculator = new CanyonCarver();
	}

	public void doIteration() {
		for (Point point : points) {
			point.calcNextState();
		}
		for (Point point : points) {
			point.flush();
		}
	}

	public void fillWithRandom(long seed) {
		Random random = new Random(seed);

		for (int idx = 0; idx < points.length; idx++) {
			points[idx].setValue(random.nextInt(256));
		}
	}

	public Color getColor(int x, int y) {
		return colorLookup[points[y * width + x].getValue()];
	}

	public int getHeight() {
		return height;
	}

	public Point getPoint(int x, int y) {
		return points[y * width + x];
	}

	public Point[] getPoints() {
		return points;
	}

	public int getWidth() {
		return width;
	}

	public void init() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				points[y * width + x] = new Point();
			}
		}

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				points[y * width + x].init(this, x, y);
				points[y * width + x].setCalculator(calculator);
			}
		}
	}
}
