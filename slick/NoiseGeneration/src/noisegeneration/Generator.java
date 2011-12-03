/*
 * Public Domain
 */
package noisegeneration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public abstract class Generator {

	protected List<Double> points = new ArrayList<Double>();

	public void generate(long seed, int width, int resolution) throws SlickException {
		points.clear();
		Random random = new Random(seed);

		for (int idx = 0; idx < width; idx += resolution) {
			points.add(random.nextDouble());
		}
	}

	protected int getStep(int width) {
		return width / (points.size() - 1);
	}

	abstract public void render(GameContainer gc, Graphics grphcs) throws SlickException;
}
