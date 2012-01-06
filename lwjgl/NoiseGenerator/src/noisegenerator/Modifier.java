/*
 * Public Domain
 */
package noisegenerator;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public interface Modifier {

	public void modify(float[] values, int width, int height);
}
