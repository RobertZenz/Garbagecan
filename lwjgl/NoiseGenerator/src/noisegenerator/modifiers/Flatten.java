/*
 * Public Domain
 */
package noisegenerator.modifiers;

import noisegenerator.Modifier;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Flatten implements Modifier {

	public void modify(float[] values, int width, int height) {
		for (int idxX = 0; idxX < width; idxX++) {
			for (int idxY = 0; idxY < height; idxY++) {
				int counter = 0;
				float newValue = 0;
				for (int neighborIdxX = Math.max(idxX - 1, 0); neighborIdxX < Math.min(idxX + 1, width); neighborIdxX++) {
					for (int neighborIdxY = Math.max(idxY - 1, 0); neighborIdxY < Math.min(idxY + 1, height); neighborIdxY++) {
						newValue += values[neighborIdxY * width + neighborIdxX];
						counter++;
					}
				}

				values[idxY * width + idxX] = newValue / counter;
			}
		}
	}
}
