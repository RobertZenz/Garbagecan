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

	private int stepWidth = 50;
	private boolean useSeed = false;
	private long seed = 0;

	public long getSeed() {
		return seed;
	}

	public void setSeed(long seed) {
		this.seed = seed;
	}

	public int getStepWidth() {
		return stepWidth;
	}

	public void setStepWidth(int stepWidth) {
		this.stepWidth = stepWidth;
	}

	public boolean isUseSeed() {
		return useSeed;
	}

	public void setUseSeed(boolean useSeed) {
		this.useSeed = useSeed;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		int smallWidth = getWidth() / stepWidth + 1;
		int smallHeight = getHeight() / stepWidth + 1;

		double[] values = null;
		if (useSeed) {
			values = NoiseMaker.getNoise(seed, smallWidth, smallHeight);
		} else {
			values = NoiseMaker.getNoise(smallWidth, smallHeight);
		}
		values = NoiseMaker.stretchArrayCosine(smallWidth, smallHeight, values, stepWidth);

		for (int x = 0; x < smallWidth * stepWidth; x++) {
			for (int y = 0; y < smallHeight * stepWidth; y++) {
				double value = values[y * smallWidth * stepWidth + x];
				int colorValue = 128 + (int) (value * 128);

				g.setColor(new Color(colorValue, colorValue, colorValue));
				g.fillRect(x, y, 1, 1);
			}
		}


		g.setColor(Color.ORANGE);
		if (useSeed) {
			g.drawString(Long.toString(seed), 5, 25);
		} else {
			g.drawString("Default", 5, 25);
		}
	}
}
