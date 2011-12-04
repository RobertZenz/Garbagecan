/*
 * Public Domain
 */
package noisegeneration.maps;

import noisegeneration.Helper;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Cosine extends Map {

	private Image buffer;

	@Override
	public void generate(long seed, int width, int resolution) throws SlickException {
		super.generate(seed, width, resolution);

		buffer = new Image(width, width);
		Graphics g = buffer.getGraphics();
		for (int x = 0; x < width / resolution - 1; x++) {
			for (int y = 0; y < width / resolution - 1; y++) {
				int idxTopLeft = x * resolution + y;
				int idxTopRight = (x + 1) * resolution + y;
				int idxBottomLeft = x * resolution + (y + 1);
				int idxBottomRight = (x + 1) * resolution + (y + 1);

				double valueTopLeft = points.get(idxTopLeft);
				double valueTopRight = points.get(idxTopRight);
				double valueBottomLeft = points.get(idxBottomLeft);
				double valueBottomRight = points.get(idxBottomRight);

				for (int x2 = x * resolution; x2 < (x + 1) * resolution; x2++) {
					int relativeX = x2 - x * resolution;
					double valueTop = Helper.cosineInterpolate(valueTopLeft, valueTopRight, (double) relativeX / resolution);
					double valueBottom = Helper.cosineInterpolate(valueBottomLeft, valueBottomRight, (double) relativeX / resolution);
					for (int y2 = y * resolution; y2 < (y + 1) * resolution; y2++) {
						int relativeY = y2 - y * resolution;
						double value = Helper.cosineInterpolate(valueTop, valueBottom, (double) relativeY / resolution);

						Color color = Color.black;
						color.r = color.g = color.b = (float) value;
						g.setColor(color);
						g.fillRect(x2, y2, 1, 1);
					}
				}
			}
		}
		g.flush();
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.drawImage(buffer, 0, 0);
		super.render(container, g);
	}
}
