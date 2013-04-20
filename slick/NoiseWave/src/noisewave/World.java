/*
 * Public Domain
 */
package noisewave;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class World extends BasicGame {

	private Segment[] segments;

	public World(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		segments = new Segment[20];

		createNoise(container.getHeight() / 2, container.getWidth() / segments.length);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();

		if (input.isKeyPressed(Input.KEY_ADD) || input.isKeyPressed(Input.KEY_LEFT)) {
			segments = new Segment[segments.length + 1];
			createNoise(container.getHeight() / 2, container.getWidth() / segments.length);
		}
		if (input.isKeyPressed(Input.KEY_SUBTRACT) || input.isKeyPressed(Input.KEY_RIGHT)) {
			segments = new Segment[segments.length - 1];
			createNoise(container.getHeight() / 2, container.getWidth() / segments.length);
		}


		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		int xStep = container.getWidth() / segments.length;
		int middle = container.getHeight() / 2;

		for (int idx = 0; idx < segments.length; idx++) {
			Segment segment = segments[idx];

			g.setColor(Color.yellow);
			render(g, segment.getLinear(), xStep, idx, middle);

			g.setColor(Color.green);
			render(g, segment.getCosine(), xStep, idx, middle);

			g.setColor(Color.cyan);
			render(g, segment.getCubic(), xStep, idx, middle);

			g.setColor(Color.red);
			g.drawRect(idx * xStep - 1, middle + segment.getStart() - 1, 2, 2);
		}
	}

	public void render(Graphics g, int[] interpolated, int xStep, int idx, int middle) {
		for (int resIdx = 0; resIdx < xStep - 1; resIdx++) {
			g.drawLine((xStep * idx) + resIdx, middle + interpolated[resIdx], (xStep * idx) + resIdx + 1, middle + interpolated[resIdx + 1]);
		}
	}

	private void createNoise(int height, int resolution) {
		SimplexNoise noise = new SimplexNoise(0L);
		for (int idx = 0; idx < segments.length; idx++) {
			segments[idx] = new Segment(
					(int) (noise.noise(idx, 0) * height),
					(int) (noise.noise(idx + 1, 0) * height),
					resolution);
		}

		for (int idx = 0; idx < segments.length; idx++) {
			if (idx > 0) {
				segments[idx].setNeighborLeft(segments[idx - 1]);
			}
			if (idx < segments.length - 1) {
				segments[idx].setNeighborRight(segments[idx + 1]);
			}

			segments[idx].interpolate();
		}
	}
}
