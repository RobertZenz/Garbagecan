/*
 * Public Domain
 */
package simplex;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class NoiseMaker {

	public static double[] getNoise(int width, int height) {
		return getNoise(new SimplexNoise(), width, height);
	}

	public static double[] getNoise(long seed, int width, int height) {
		return getNoise(new SimplexNoise(seed), width, height);
	}

	public static double[] getNoise(SimplexNoise simplex, int width, int height) {
		double[] values = new double[width * height];

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				values[y * width + x] = simplex.noise(x, y);
			}
		}

		return values;
	}

	public static double[] stretchArrayCosine(
			int originalWidth, int originalHeight, double[] originalValues,
			int factor) {

		double[] values = new double[(originalWidth * factor) * (originalHeight * factor)];


		for (int x = 0; x < originalWidth - 1; x++) {
			for (int y = 0; y < originalHeight - 1; y++) {
				double topLeft = originalValues[y * originalWidth + x];
				double topRight = originalValues[y * originalWidth + (x + 1)];
				double bottomLeft = originalValues[(y + 1) * originalWidth + x];
				double bottomRight = originalValues[(y + 1) * originalWidth + (x + 1)];

				for (int newX = 0; newX < factor; newX++) {
					double currentTop = Helper.cosineInterpolate(topLeft, topRight, (float) newX / factor);
					double currentBottom = Helper.cosineInterpolate(bottomLeft, bottomRight, (float) newX / factor);

					int currentX = (x * factor) + newX;

					for (int newY = 0; newY < factor; newY++) {
						double current = Helper.cosineInterpolate(currentTop, currentBottom, (float) newY / factor);
						int currentY = (y * factor) + newY;

						values[currentY * (originalWidth * factor) + currentX] = current;
					}
				}
			}
		}

		return values;
	}
}
