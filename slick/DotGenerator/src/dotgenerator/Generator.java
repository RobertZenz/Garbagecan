/*
 * Public Domain
 */
package dotgenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Generator {

	private int count;
	private int height;
	private long seed;
	private int width;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public long getSeed() {
		return seed;
	}

	public void setSeed(long seed) {
		this.seed = seed;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Generator(int count, int height, long seed, int width) {
		this.count = count;
		this.height = height;
		this.seed = seed;
		this.width = width;
	}

	public List<Vector2f> randomDistribution() {
		List<Vector2f> points = new ArrayList<Vector2f>();

		Random random = new Random(seed);

		while (points.size() < count) {
			points.add(new Vector2f(random.nextInt(width), random.nextInt(height)));
		}

		return points;
	}

	public List<Vector2f> randomEvenlyDistribution() {
		List<Vector2f> points = new ArrayList<Vector2f>();

		Random random = new Random(seed);

		int part = (int) Math.sqrt(width * height / count);

		for (int y = 0; y < height; y += part) {
			for (int x = 0; x < width; x += part) {
				points.add(new Vector2f(x + random.nextInt(part), y + random.nextInt(part)));
			}
		}

		return points;
	}

	public List<Vector2f> randomCircle() {
		List<Vector2f> points = new ArrayList<Vector2f>();

		Random random = new Random(seed);

		int segments = 5;
		int part = width / 2 / segments;
		int pointsPerSegment = count / segments;

		for (int segment = 0; segment < segments; segment++) {
			int segmentBegins = segment * part;

			for (int counter = 0; counter < pointsPerSegment; counter++) {
				float distance = (segment * part) + random.nextInt(part);
				float angle = (float) Math.toRadians(random.nextInt(360));

				points.add(new Vector2f(
						(float) Math.cos(angle) * distance,
						(float) Math.sin(angle) * distance));
			}
		}

		return points;
	}
}
