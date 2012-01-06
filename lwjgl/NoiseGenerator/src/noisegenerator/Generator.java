/*
 * Public Domain
 */
package noisegenerator;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public interface Generator {

	public void init(long seed, float minValue, float maxValue);

	public float getValue(int x, int y);
}
