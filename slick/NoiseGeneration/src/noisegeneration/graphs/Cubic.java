/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package noisegeneration.graphs;

import java.util.ArrayList;
import java.util.List;
import noisegeneration.Helper;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author robert
 */
public class Cubic extends Graph {

	private List<Double> interpolatedPoints = new ArrayList<Double>();

	@Override
	public void generate(long seed, int width, int resolution) throws SlickException {
		super.generate(seed, width, resolution);

		interpolatedPoints.clear();

		int step = getStep(width);
		for (int idx = 1; idx < points.size(); idx++) {
			double lastLastPoint = points.get((idx - 2) >= 0 ? idx - 2 : idx - 1);
			double lastPoint = points.get(idx - 1);
			double nextPoint = points.get(idx);
			double nextNextPoint = points.get((idx + 1) < points.size() ? idx + 1 : idx);
			for (int stepper = 0; stepper < step; stepper++) {
				interpolatedPoints.add(Helper.cubicInterpolate(lastLastPoint, lastPoint, nextPoint, nextNextPoint, (double) stepper / step));
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
