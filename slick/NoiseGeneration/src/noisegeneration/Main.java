/*
 * Public Domain
 */
package noisegeneration;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author robert
 */
public class Main extends BasicGame {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new Main("WorldGenerationTest"));
			app.setDisplayMode(800, 600, false);
			app.setSmoothDeltas(true);
			app.setTargetFrameRate(60);
			app.start();
		} catch (SlickException ex) {
			System.err.print(ex);
			System.exit(0);
		}
	}
	private Generator generator;
	private long seed = 0;
	private int resolution = 50;

	public Main(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		generator = new noisegeneration.graphs.NoInterpolation();
		generator.generate(seed, gc.getWidth(), resolution);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();

		if (input.isKeyPressed(Input.KEY_F1)) {
			generator = new noisegeneration.graphs.NoInterpolation();
			generator.generate(seed, gc.getWidth(), resolution);
		}
		if (input.isKeyPressed(Input.KEY_F2)) {
			generator = new noisegeneration.graphs.Linear();
			generator.generate(seed, gc.getWidth(), resolution);
		}
		if (input.isKeyPressed(Input.KEY_F3)) {
			generator = new noisegeneration.graphs.Cosine();
			generator.generate(seed, gc.getWidth(), resolution);
		}
		if (input.isKeyPressed(Input.KEY_F4)) {
			generator = new noisegeneration.graphs.Cubic();
			generator.generate(seed, gc.getWidth(), resolution);
		}

		if (input.isKeyPressed(Input.KEY_F5)) {
			generator = new noisegeneration.maps.NoInterpolation();
			generator.generate(seed, gc.getWidth(), resolution);
		}
		if (input.isKeyPressed(Input.KEY_F6)) {
			generator = new noisegeneration.maps.Linear();
			generator.generate(seed, gc.getWidth(), resolution);
		}
		if (input.isKeyPressed(Input.KEY_F7)) {
			generator = new noisegeneration.maps.Cosine();
			generator.generate(seed, gc.getWidth(), resolution);
		}

		if (input.isKeyPressed(Input.KEY_F12)) {
			generator.setDrawDots(!generator.getDrawDots());
		}

		if (input.isKeyDown(Input.KEY_A)) {
			resolution--;
			generator.generate(seed, gc.getWidth(), resolution);
		}
		if (input.isKeyDown(Input.KEY_D)) {
			resolution++;
			generator.generate(seed, gc.getWidth(), resolution);
		}
		if (input.isKeyDown(Input.KEY_W)) {
			seed++;
			generator.generate(seed, gc.getWidth(), resolution);
		}
		if (input.isKeyDown(Input.KEY_S)) {
			seed--;
			if (seed < 0) {
				seed = 0;
			}
			generator.generate(seed, gc.getWidth(), resolution);
		}

		if (input.isKeyPressed(Input.KEY_RETURN)) {
			generator.generate(seed, gc.getWidth(), resolution);
		}

		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			gc.exit();
		}
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		generator.render(container, g);

		int x = 10;
		int y = 15;
		int height = 13;
		g.setColor(Color.green);
		g.drawString("Seed: " + Long.toString(seed), x, y += height);

		g.setColor(Color.blue);
		g.drawString("", x, y += height);
		g.drawString("Graphs:", x, y += height);
		g.drawString("F1 - None", x, y += height);
		g.drawString("F2 - Linear", x, y += height);
		g.drawString("F3 - Cosine", x, y += height);
		g.drawString("F4 - Cubic", x, y += height);

		g.drawString("", x, y += height);
		g.drawString("Maps:", x, y += height);
		g.drawString("F5 - None", x, y += height);
		g.drawString("F6 - Linear", x, y += height);
		g.drawString("F7 - Cosine", x, y += height);

		g.drawString("", x, y += height);
		g.drawString("F12 - Draw Dots", x, y += height);
	}
}
