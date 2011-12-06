/*
 * Public Domain
 */
package noisegeneration.maps;

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
 * @author Robert 'Bobby' Zenz
 */
public class Map implements Generator {

	protected boolean drawDots;

	public boolean getDrawDots() {
		return drawDots;
	}

	public void setDrawDots(boolean drawDots) {
		this.drawDots = drawDots;
	}
	protected int resolution;
	protected List<Double> points = new ArrayList<Double>();

	public void generate(long seed, int width, int resolution) throws SlickException {
		Random random = new Random(seed);
		this.resolution = resolution;

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < width; y++) {
				points.add(random.nextDouble());
			}
		}
	}

	protected int getStep(int width) {
		return width / (points.size() - 1);
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		if (drawDots) {
			int width = container.getWidth();
			int height = container.getHeight();
			int step = getStep(container.getWidth());

			g.setColor(Color.red);
			for (int x = 0; x < container.getWidth() / resolution; x++) {
				for (int y = 0; y < container.getHeight() / resolution; y++) {
					int positionX = x * resolution;
					int positionY = y * resolution;
					g.drawOval(positionX - 5, positionY - 5, 11, 11);
					g.drawLine(positionX - 5, positionY, positionX + 4, positionY);
					g.drawLine(positionX, positionY - 5, positionX, positionY + 4);
				}
			}
		}
	}
}
