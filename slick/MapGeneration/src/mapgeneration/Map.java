/*
 * Pulic Domain
 */
package mapgeneration;

import java.util.Random;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Map {

	private Tile[] tiles;
	private int width;
	private int height;

	public Map(int width, int height) {
		tiles = new Tile[width * height];

		this.width = width;
		this.height = height;

		for (int idxX = 0; idxX < width; idxX++) {
			for (int idxY = 0; idxY < height; idxY++) {
				tiles[idxY * width + idxX] = new Tile();
			}
		}
	}

	public Tile[] getTiles() {
		return tiles;
	}

	public Tile getTile(int x, int y) {
		return tiles[y * width + x];
	}

	public Tile getTile(int index) {
		return tiles[index];
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void init() {
		for (int idxX = 0; idxX < width; idxX++) {
			for (int idxY = 0; idxY < height; idxY++) {
				tiles[idxY * width + idxX].init(this, idxX, idxY);
			}
		}
	}

	public void generate(long seed) {
		Random random = new Random(seed);

		TileType[] values = TileType.values();

		for (Tile tile : tiles) {
			tile.setType(values[random.nextInt(values.length - 1) + 1]);
		}
	}

	public void doGeneration() {
		for (Tile tile : tiles) {
			tile.doGeneration();
		}
		for (Tile tile : tiles) {
			tile.flush();
		}
	}
}
