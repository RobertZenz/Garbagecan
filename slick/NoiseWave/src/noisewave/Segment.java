/*
 * Public Domain
 */
package noisewave;

public class Segment {

	private int start;
	private int end;
	private int[] interpolated;
	private int resolution;

	public int getEnd() {
		return end;
	}

	public int[] getInterpolated() {
		return interpolated;
	}

	public int getStart() {
		return start;
	}

	public Segment(int start, int end, int resolution) {
		this.start = start;
		this.end = end;
		this.resolution = resolution;

		this.interpolate();
	}

	private void interpolate() {
		interpolated = new int[resolution];
		for (int count = 0; count < resolution; count++) {
			interpolated[count] = (int) (Interpolate.cosine(start, end, (float) count / resolution));
		}
	}
}
