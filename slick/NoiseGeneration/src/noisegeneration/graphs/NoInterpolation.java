/*
 * Public Domain
 */
package noisegeneration.graphs;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author robert
 */
public class NoInterpolation extends Graph {

	@Override
	public void render(GameContainer gc, Graphics grphcs) throws SlickException {
		super.render(gc, grphcs);

		grphcs.setColor(Color.white);

		int step = getStep(gc.getWidth());
		int height = gc.getHeight();
		int lastHeight = height - (int) (height * points.get(0));

		for (int idx = 0; idx < points.size(); idx++) {
			int nextHeight = height - (int) (height * points.get(idx));
			grphcs.drawLine((idx-1) * step, lastHeight, idx * step, lastHeight);
			grphcs.drawLine(idx * step, lastHeight, idx * step, nextHeight);
			lastHeight = nextHeight;
		}
	}
}
