/*
 * Public Domain
 */
package simplex;

import java.awt.Color;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class ColorRange {

	private double min;
	private double max;
	private Color color;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public ColorRange(double min, double max, Color color) {
		this.min = min;
		this.max = max;
		this.color = color;
	}
}
