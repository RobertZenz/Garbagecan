/*
 * Public Domain
 */
package simplex;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author robert
 */
public class NoiseCanvas extends Canvas {

	private List<ColorRange> colorRanges = new ArrayList<ColorRange>();
	private int stepWidth = 50;
	private boolean useColor = false;
	private boolean useCosine = true;
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

	public List<ColorRange> getColorRanges() {
		return colorRanges;
	}

	public void setColorRanges(List<ColorRange> colorRanges) {
		this.colorRanges = colorRanges;
	}

	public boolean isUseColor() {
		return useColor;
	}

	public void setUseColor(boolean useColor) {
		this.useColor = useColor;
	}

	public boolean isUseCosine() {
		return useCosine;
	}

	public void setUseCosine(boolean useCosine) {
		this.useCosine = useCosine;
	}

	public NoiseCanvas() {
		super();

		colorRanges.add(new ColorRange(-1, -0.75, Color.black));
		colorRanges.add(new ColorRange(-0.75, -0.5, Color.blue));
		colorRanges.add(new ColorRange(-0.5, -0.25, Color.cyan));
		colorRanges.add(new ColorRange(0, 0.25, Color.yellow));
		colorRanges.add(new ColorRange(0.25, 0.5, Color.orange));
		colorRanges.add(new ColorRange(0.5, 0.75, Color.green));
		colorRanges.add(new ColorRange(0.75, 1.01, Color.white));
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
		if(useCosine) {
		values = NoiseMaker.stretchArrayCosine(smallWidth, smallHeight, values, stepWidth);
		} else {
		values = NoiseMaker.stretchArrayLinear(smallWidth, smallHeight, values, stepWidth);
		}
		for (int x = 0; x < smallWidth * stepWidth; x++) {
			for (int y = 0; y < smallHeight * stepWidth; y++) {
				double value = values[y * smallWidth * stepWidth + x];
				if (useColor) {
					for (ColorRange range : colorRanges) {
						if (value >= range.getMin() && value < range.getMax()) {
							g.setColor(range.getColor());
						}
					}
				} else {
					int colorValue = 128 + (int) (value * 128);
					g.setColor(new Color(colorValue, colorValue, colorValue));
				}
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
