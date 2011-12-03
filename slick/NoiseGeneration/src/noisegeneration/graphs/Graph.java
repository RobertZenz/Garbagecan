/*
 * Public Domain
 */
package noisegeneration.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import noisegeneration.Generator;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author robert
 */
public class Graph implements Generator {

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

	public void render(GameContainer gc, Graphics grphcs) throws SlickException {
		int height = gc.getHeight();
		int step = getStep(gc.getWidth());

		grphcs.setColor(Color.red);
		for (int idx = 0; idx < points.size(); idx++) {
			int position = height - (int) (height * points.get(idx));
			int positionX = idx * step;
			grphcs.drawOval(positionX - 5, position - 5, 11, 11);
			grphcs.drawLine(positionX - 5, position, positionX + 4, position);
			grphcs.drawLine(positionX, position - 5, positionX, position + 4);
		}
	}
}
