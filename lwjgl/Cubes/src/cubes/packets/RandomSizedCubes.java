/*
 * Public Domain
 */
package cubes.packets;

import cubes.CubeRenderer;
import java.util.Random;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class RandomSizedCubes implements cubes.Packet {

	private int[] coordinates;
	private int[] sizes;

	public RandomSizedCubes(long seed) {
		Random random = new Random(seed);

		sizes = new int[random.nextInt(85)];
		coordinates = new int[sizes.length * 3];

		for (int idx = 0; idx < coordinates.length; idx++) {
			coordinates[idx] = random.nextInt(1000);
		}
		for (int idx = 0; idx < sizes.length; idx++) {
			sizes[idx] = random.nextInt(50) + 1;
		}
	}

	public void render() {
		for (int idx = 0; idx < coordinates.length; idx += 3) {
			CubeRenderer.render(coordinates[idx], coordinates[idx + 1], coordinates[idx + 2], sizes[idx / 3]);
		}
	}
}
