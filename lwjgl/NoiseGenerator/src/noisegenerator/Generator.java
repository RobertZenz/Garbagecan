/*
 * Public Domain
 */
package noisegenerator;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public interface Generator {

	public float getMinValue();

	public float getMaxValue();

	public float getValue(int x, int y);

	public void init(long seed, float minValue, float maxValue);
}
