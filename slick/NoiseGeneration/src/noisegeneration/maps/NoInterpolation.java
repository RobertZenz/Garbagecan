/*
 * Public Domain
 */
package noisegeneration.maps;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class NoInterpolation extends Map {

	private Image buffer;

	@Override
	public void generate(long seed, int width, int resolution) throws SlickException {
		super.generate(seed, width, resolution);

		buffer = new Image(width, width);
		Graphics g = buffer.getGraphics();
		for (int x = 0; x < width / resolution; x++) {
			for (int y = 0; y < width / resolution; y++) {
				double value = points.get(x * resolution + y);
				Color color = Color.black;
				color.r = color.g = color.b = (float) value;
				g.setColor(color);
				int positionX = x * resolution;
				int positionY = y * resolution;
				g.fillRect(x * resolution, y * resolution, resolution, resolution);
			}
		}
		g.flush();
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.drawImage(buffer, 0, 0);
		super.render(gc, g);
	}
}
