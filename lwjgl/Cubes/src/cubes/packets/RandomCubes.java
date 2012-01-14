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
public class RandomCubes implements cubes.Packet {

	private int[] coordinates;
	private int size = 25;

	public RandomCubes(long seed) {
		Random random = new Random(seed);

		coordinates = new int[random.nextInt(1000)];

		for (int idx = 0; idx < coordinates.length; idx++) {
			coordinates[idx] = random.nextInt(1000);
		}
	}

	public void render() {
		for (int idx = 0; idx < coordinates.length; idx += 3) {
			CubeRenderer.render(coordinates[idx], coordinates[idx + 1], coordinates[idx + 2], size);
		}
	}
}
