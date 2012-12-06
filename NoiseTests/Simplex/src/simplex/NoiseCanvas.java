/*
 * Public Domain
 */
package simplex;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author robert
 */
public class NoiseCanvas extends Canvas {

	private int stepWidth = 10;

	public int getStepWidth() {
		return stepWidth;
	}

	public void setStepWidth(int stepWidth) {
		this.stepWidth = stepWidth;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		int stepsWidth = (int) (getWidth() / stepWidth);
		int stepsHeight = (int) (getHeight() / stepWidth);

		int valueCount = stepsWidth * stepsHeight;
		Double[] values = new Double[valueCount];

		for (int x = 0; x < stepsWidth; x++) {
			for (int y = 0; y < stepsHeight; y++) {
				values[y * stepsWidth + x] = SimplexNoise.noise(x, y);
			}
		}

		for (int x = 0; x < stepsWidth; x++) {
			for (int y = 0; y < stepsHeight; y++) {
				double topLeft = values[y * stepsWidth + x];
				double topRight = values[y * stepsWidth + (x + 1)];
				double bottomLeft = values[(y + 1) * stepsWidth + x];
				double bottomRight = values[(y + 1) * stepsWidth + (x + 1)];

				for (int detailX = 0; detailX < stepsWidth; detailX++) {
					double currentLeft = Helper.cosineInterpolate(topLeft, bottomLeft, detailX / stepsWidth);
					double currentRight = Helper.cosineInterpolate(topRight, bottomRight, detailX / stepsWidth);

					for (int detailY = 0; detailY < stepsHeight; detailY++) {
						double current = Helper.cosineInterpolate(currentLeft, currentRight, detailY / stepsHeight);
						int value = (int) (current * 128 + 128);
						g.setColor(new Color(value, value, value));
						g.fillRect(x * stepsWidth + detailX, y * stepsHeight + detailY, 1, 1);
					}
				}
			}
		}
	}
}
