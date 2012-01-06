/*
 * Public Domain
 */
package noisegenerator.generators;

import noisegenerator.Generator;
import java.util.Random;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class PureRandom implements Generator {

	private Random random;
	private float minValue;
	private float maxValue;

	public float getMaxValue() {
		return maxValue;
	}

	public float getMinValue() {
		return minValue;
	}

	public float getValue(int x, int y) {
		return random.nextFloat() * (maxValue - minValue) + minValue;
	}

	public void init(long seed, float minValue, float maxValue) {
		random = new Random(seed);

		this.minValue = minValue;
		this.maxValue = maxValue;
	}
}
