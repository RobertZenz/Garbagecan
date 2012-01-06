/*
 * Public Domain
 */
package noisegenerator.modifiers;

import noisegenerator.Modifier;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class SineWave implements Modifier {

	private float angleStart = 0f;
	private float heightMod = 1f;

	public SineWave(float heightMod) {
		this.heightMod = heightMod;
	}

	public void modify(float[] values, int width, int height) {
		for (int idxX = 0; idxX < width; idxX++) {
			for (int idxY = 0; idxY < height; idxY++) {
				values[idxY * width + idxX] = (float) Math.sin(Math.toDegrees((idxX + angleStart) / 360)) * heightMod;
			}
		}
		angleStart += 0.5f;
		if (angleStart > 360) {
			angleStart -= 360;
		}
	}
}
