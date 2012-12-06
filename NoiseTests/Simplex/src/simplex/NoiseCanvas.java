package simplex;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author robert
 */
public class NoiseCanvas extends Canvas {

	private int stepWidth = 5;

	public int getStepWidth() {
		return stepWidth;
	}

	public void setStepWidth(int stepWidth) {
		this.stepWidth = stepWidth;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		for (int x = 0; x < getWidth(); x+=stepWidth) {
			for (int y = 0; y < getHeight(); y+=stepWidth) {
				int value = (int) (SimplexNoise.noise(x, y) * 128 + 128);
				g.setColor(new Color(value, value, value));
				g.fillRect(x, y, stepWidth, stepWidth);
			}
		}
	}
}
