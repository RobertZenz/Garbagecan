/*
 * Public Domain
 */
package smoothing;

public final class Interpolate {

	/**
	 *
	 * @param a First value
	 * @param b Second value
	 * @param offseet 0 = a, 1 = b
	 * @return
	 */
	public static double linear(double a, double b, double offset) {
		return a * (1 - offset) + b * offset;
	}

	public static double cosine(double a, double b, double offset) {
		double temp = (1 - Math.cos(offset * Math.PI)) * 0.5;
		return a * (1 - temp) + b * temp;
	}

	/**
	 *
	 * @param a First Value
	 * @param b First Target
	 * @param m Second Target
	 * @param n Second Value
	 * @param offset
	 * @return
	 */
	public static double cubic(double a, double b, double m, double n, double offset) {
		double p = (n - m) - (a - b);
		double q = (a - b) - p;
		double r = m - a;

		return p * Math.pow(offset, 3) + q * Math.pow(offset, 2) + (r * offset) + b;
	}
}
