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

	public int getStepWidth() {
		return stepWidth;
	}

	public void setStepWidth(int stepWidth) {
		this.stepWidth = stepWidth;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		int smallWidth = getWidth() / stepWidth;
		int smallHeight = getHeight() / stepWidth;


		double[] values = NoiseMaker.getNoise(smallWidth, smallHeight);
		values = NoiseMaker.stretchArrayCosine(smallWidth, smallHeight, values, stepWidth);

		for (int x = 0; x < smallWidth * stepWidth; x++) {
			for(int y = 0; y < smallHeight * stepWidth; y++) {
				double value = values[y * smallWidth * stepWidth + x];
				int colorValue = 128 + (int)(value * 128);
				
				g.setColor(new Color(colorValue, colorValue, colorValue));
				g.fillRect(x, y, 1, 1);
			}
		}
	}
}
