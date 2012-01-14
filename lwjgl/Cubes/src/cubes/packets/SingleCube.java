/*
 * Public Domain
 */
package cubes.packets;

import cubes.CubeRenderer;
import cubes.Packet;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class SingleCube implements Packet {

	public void render() {
		CubeRenderer.render(0, 0, 0, 50);
	}
}
