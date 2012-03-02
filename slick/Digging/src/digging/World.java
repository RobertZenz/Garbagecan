/*
 * Public Domain
 */
package digging;

import digging.entities.Digger;
import digging.tiles.Air;
import digging.tiles.Dirt;
import digging.tiles.Tunnel;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class World {

	private Tile[] tiles;
	private List<Entity> diggers = new ArrayList<Entity>();
	private int tilesX;
	private int tilesY;

	public World(int tilesX, int tilesY) {
		this.tilesX = tilesX;
		this.tilesY = tilesY;

		init();
	}

	public Tile getTile(int x, int y) {
		return tiles[y * tilesX + x];
	}

	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();

		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			int pressedX = input.getMouseX() / (int) Tile.SIZE;
			int pressedY = input.getMouseY() / (int) Tile.SIZE;

			if (pressedX < tilesX && pressedY < tilesY) {
				if (tiles[pressedY * tilesX + pressedX].isDiggable()) {
					tiles[pressedY * tilesX + pressedX] = new Tunnel();
				}
			}
		}

		for (Entity digger : diggers) {
			digger.update(container, delta);
		}
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		for (int x = 0; x < tilesX; x++) {
			for (int y = 0; y < tilesY; y++) {
				tiles[y * tilesX + x].render(container, g, new Rectangle(x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE));
			}
		}

		for (Entity digger : diggers) {
			digger.render(container, g);
		}
	}

	private void init() {
		tiles = new Tile[tilesY * tilesX];

		for (int x = 0; x < tilesX; x++) {
			for (int y = 0; y < tilesY; y++) {
				tiles[y * tilesX + x] = new Dirt();
			}
		}

		tiles[1] = new Air();
		tiles[2] = new Air();
		
		diggers.add(new Digger(this, Tile.SIZE + Tile.SIZE / 2, Tile.SIZE - Tile.SIZE / 2));
	}
}
