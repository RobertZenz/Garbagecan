/*
 * Public Domain
 */
package cellularautomata;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public interface Calculator {

	public int calcNextValue(Iterable<Point> neighbors, int originalValue);
}
