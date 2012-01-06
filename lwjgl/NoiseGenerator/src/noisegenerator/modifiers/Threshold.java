/*
 * Public Domain
 */
package noisegenerator.modifiers;

import noisegenerator.Modifier;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Threshold implements Modifier {

	private float threshold;

	public Threshold(float threshold) {
		this.threshold = threshold;
	}

	public void modify(float[] values, int width, int height) {
		for (int idxX = 0; idxX < width; idxX++) {
			for (int idxY = 0; idxY < height; idxY++) {

				float value = values[idxY * width + idxX];
				if (value < threshold && value > 0) {
					values[idxY * width + idxX] -= 0.1f;
				}
			}
		}
	}
}
