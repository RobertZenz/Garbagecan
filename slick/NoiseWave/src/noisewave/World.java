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

		if (input.isKeyPressed(Input.KEY_ADD)) {
			segments = new Segment[segments.length + 1];
			createNoise(container.getHeight() / 2, container.getWidth() / segments.length);
		}
		if (input.isKeyPressed((Input.KEY_SUBTRACT))) {
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

			g.setColor(Color.white);
			int[] interpolated = segment.getInterpolated();
			for (int resIdx = 0; resIdx < xStep - 1; resIdx++) {
				g.drawLine((xStep * idx) + resIdx, middle + interpolated[resIdx], (xStep * idx) + resIdx + 1, middle + interpolated[resIdx + 1]);
			}

			g.setColor(Color.red);
			g.drawRect(idx * xStep, middle + segment.getStart(), 1, 1);
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
	}
}
