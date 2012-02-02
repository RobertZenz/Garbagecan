/*
 * Public Domain
 */
package mapgeneration;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Tile {

	private TileType type = TileType.UNKNOWN;
	private TileType newType = TileType.UNKNOWN;
	private List<Tile> neighbors = new ArrayList<Tile>();

	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
		this.type = type;
	}

	public void init(Map parent, int x, int y) {
		neighbors.clear();

		for (int neighborX = Math.max(0, x - 1); neighborX < Math.min(parent.getWidth(), x + 2); neighborX++) {
			for (int neighborY = Math.max(0, y - 1); neighborY < Math.min(parent.getHeight(), y + 2); neighborY++) {
				if (neighborX != x || neighborY != y) {
					neighbors.add(parent.getTile(neighborX, neighborY));
				}
			}
		}
	}

	public void doGeneration() {
		newType = type;

		switch (type) {
			case DESERT:
				if ((getNeighborCount(TileType.WATER) + getNeighborCount(TileType.SNOW)) >= 3) {
					newType = TileType.GRASSLAND;
				}
				if(getNeighborCount(TileType.GRASSLAND) > 4) {
					newType = TileType.GRASSLAND;
				}
				break;

			case GRASSLAND:
				break;

			case SNOW:
				if(getNeighborCount(TileType.GRASSLAND) > 3) {
					newType = TileType.GRASSLAND;
				}
				break;

			case STONE:
				if (getNeighborCount(TileType.GRASSLAND) >= 3) {
					newType = TileType.GRASSLAND;
				}
				if (getNeighborCount(TileType.SNOW) > 2) {
					newType = TileType.SNOW;
				}
				break;

			case UNKNOWN:
				break;

			case WATER:
				if (getNeighborCount(TileType.GRASSLAND) >= 3) {
					newType = TileType.SNOW;
				}
				break;
		}

		if (getNeighborCount(TileType.WATER) >= 4) {
			newType = TileType.WATER;
		}
		if (getNeighborCount(TileType.DESERT) >= 4) {
			newType = TileType.DESERT;
		}
	}

	public void flush() {
		type = newType;
	}

	public Color getColor() {
		switch (type) {
			case DESERT:
				return Color.yellow;

			case GRASSLAND:
				return Color.green;

			case SNOW:
				return Color.white;

			case STONE:
				return Color.gray;

			case UNKNOWN:
				return Color.magenta;

			case WATER:
				return Color.blue;

			default:
				return Color.orange;
		}
	}

	public void render(Graphics g, int x, int y, int width, int height) {
		g.setColor(getColor());
		g.fillRect(x, y, width, height);

		g.setColor(Color.black);

//		int stringY = 0;
//		for (Tile neighbor : neighbors) {
//			g.drawString(neighbor.getType().name(), x, y + stringY);
//			stringY += 15;
//		}
	}

	private int getNeighborCount(TileType type) {
		int count = 0;
		for (Tile neighbor : neighbors) {
			if (neighbor.getType() == type) {
				count++;
			}
		}
		return count;
	}
}
