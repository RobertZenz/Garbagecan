/*
 * Public Domain
 */
package noisewave;

public class Segment {

	private int start;
	private int end;
	private int[] linear;
	private int[] cosine;
	private int[] cubic;

	private int resolution;

	private Segment neighborLeft;
	private Segment neighborRight;

	public int getEnd() {
		return end;
	}

	public int[] getLinear() {
		return linear;
	}

	public int[] getCosine() {
		return cosine;
	}

	public int[] getCubic() {
		return cubic;
	}

	public Segment getNeighborLeft() {
		return neighborLeft;
	}

	public void setNeighborLeft(Segment neighborLeft) {
		this.neighborLeft = neighborLeft;
	}

	public Segment getNeighborRight() {
		return neighborRight;
	}

	public void setNeighborRight(Segment neighborRight) {
		this.neighborRight = neighborRight;
	}

	public int getStart() {
		return start;
	}

	public Segment(int start, int end, int resolution) {
		this.start = start;
		this.end = end;
		this.resolution = resolution;
	}

	public void interpolate() {
		linear = new int[resolution];
		for (int count = 0; count < resolution; count++) {
			linear[count] = (int) (Interpolate.linear(start, end, (float) count / resolution));
		}

		cosine = new int[resolution];
		for (int count = 0; count < resolution; count++) {
			cosine[count] = (int) (Interpolate.cosine(start, end, (float) count / resolution));
		}

		cubic = new int[resolution];
		int a = start;
		if(neighborLeft != null) {
			a = neighborLeft.getStart();
		}
		int b = end;
		if(neighborRight != null) {
			b = neighborRight.getEnd();
		}
		for (int count = 0; count < resolution; count++) {
			cubic[count] = (int) (Interpolate.cubic(a, start, end, b, (float) count / resolution));
		}
	}
}
