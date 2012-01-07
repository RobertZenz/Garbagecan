/*
 * Public Domain
 */
package movement3;

import java.util.Random;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Terrain {

	private float[] basePoints;
	private float[] terrainPoints;

	public float getTerrainHeight(int atIndex) {
		return terrainPoints[atIndex];
	}

	public Terrain(Random random, int length, int space) {
		this.basePoints = new float[length];
		this.terrainPoints = new float[length * space];

		basePoints[0] = random.nextFloat();
		for (int idx = 1; idx < length; idx++) {
			basePoints[idx] = random.nextFloat();

			float previousPoint = basePoints[idx - 1];
			float thisPoint = basePoints[idx];
			int terrainPosition = (idx - 1) * space;
			for (int subIdx = 0; subIdx < space; subIdx++) {
				terrainPoints[terrainPosition + subIdx] = cosineInterpolation(previousPoint, thisPoint, (float) subIdx / space);
			}
		}
	}

	static float cosineInterpolation(float a, float b, float mean) {
		float temp = (float) (1 - Math.cos(mean * Math.PI)) * 0.5f;
		return a * (1 - temp) + b * temp;
	}
}
