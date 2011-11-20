/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package noisegeneration.graphs;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import noisegeneration.Helper;

/**
 *
 * @author robert
 */
public class Cosine extends Graph {

	private List<Double> interpolatedPoints = new ArrayList<Double>();

	@Override
	public void generate(long seed, int width, int resolution) throws SlickException {
		super.generate(seed, width, resolution);

		interpolatedPoints.clear();

		int step = getStep(width);
		for (int idx = 1; idx < points.size(); idx++) {
			double lastPoint = points.get(idx - 1);
			double nextPoint = points.get(idx);
			for (int stepper = 0; stepper < step; stepper++) {
				interpolatedPoints.add(Helper.cosineInterpolate(lastPoint, nextPoint, (double) stepper / step));
			}
		}
	}

	@Override
	public void render(GameContainer gc, Graphics grphcs) throws SlickException {
		super.render(gc, grphcs);

		grphcs.setColor(Color.white);

		int height = gc.getHeight();
		int lastHeight = height - (int) (height * interpolatedPoints.get(0));

		for (int idx = 1; idx < interpolatedPoints.size(); idx++) {
			int nextHeight = height - (int) (height * interpolatedPoints.get(idx));
			grphcs.drawLine(idx - 1, lastHeight, idx, nextHeight);
			lastHeight = nextHeight;
		}
	}
}