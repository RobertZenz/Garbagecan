/*
 * Public Domain
 */
package isometricworld;

import isometricworld.tiles.ColoredTile;
import isometricworld.tiles.Tile;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class World extends BasicGame {

	private int width = 12;
	private int height = 12;
	private int tileSizeWidth = 128;
	private int tileSizeWidthHalf = tileSizeWidth / 2;
	private int tileSizeHeight = 64;
	private int tileSizeHeightHalf = tileSizeHeight / 2;
	private Tile[] tiles = new Tile[width * height];
	private int highlightedTileIdxX;
	private int highlightedTileIdxY;
	private int offsetX = 0;
	private int offsetY = 0;
	private int lastCursorPosX = -1;
	private int lastCursorPosY = -1;
	private int guiSize = 48;
	private ColoredTile[] guiTiles;
	private int highlightedGui;
	private Color currentColor = Color.green;

	public World(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		guiTiles = new ColoredTile[]{
					new ColoredTile(guiSize, guiSize, Color.yellow),
					new ColoredTile(guiSize, guiSize, Color.red),
					new ColoredTile(guiSize, guiSize, Color.blue),
					new ColoredTile(guiSize, guiSize, Color.green)
				};

		for (int idx = 0; idx < tiles.length; idx++) {
			tiles[idx] = new ColoredTile(tileSizeWidth, tileSizeHeight, Color.green);
		}

		offsetX = container.getWidth() / 2;
		offsetY = container.getHeight() / 2;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();

		if (input.getMouseX() <= guiSize && input.getMouseY() <= guiSize * guiTiles.length) {
			highlightedTileIdxX = -1;
			highlightedTileIdxY = -1;

			highlightedGui = input.getMouseY() / guiSize;

			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				currentColor = guiTiles[highlightedGui].getColor();
			}
		} else {
			highlightedGui = -1;

			int mouseX = input.getMouseX() - offsetX;
			int mouseY = input.getMouseY() - offsetY;
			highlightedTileIdxX = (mouseY - mouseX / 2) / tileSizeHeight;
			highlightedTileIdxY = (mouseY + mouseX / 2) / tileSizeHeight;

			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON) && lastCursorPosX == -1) {
				if (highlightedTileIdxX >= 0 && highlightedTileIdxX < width
						&& highlightedTileIdxY >= 0 && highlightedTileIdxY < height) {
					tiles[highlightedTileIdxX * width + highlightedTileIdxY] = new ColoredTile(tileSizeWidth, tileSizeHeight, currentColor);
				}
			}

			// Dragging
			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				if (lastCursorPosX > -1) {
					offsetX += input.getMouseX() - lastCursorPosX;
					offsetY += input.getMouseY() - lastCursorPosY;
				}
				lastCursorPosX = input.getMouseX();
				lastCursorPosY = input.getMouseY();
			} else {
				lastCursorPosX = -1;
				lastCursorPosY = -1;
			}
		}

		// Exit
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		// http://gamedev.stackexchange.com/questions/12362/isometric-rendering-and-picking

		for (int x = tileSizeWidthHalf, idxX = 0; x < container.getWidth() && idxX < width; x += tileSizeWidthHalf, idxX++) {
			for (int y = tileSizeHeightHalf, idxY = 0; y < container.getHeight() && idxY < height; y += tileSizeHeightHalf, idxY++) {
				int screenX = getScreenX(idxX, idxY);
				int screenY = getScreenY(idxX, idxY);
				g.drawImage(tiles[idxX * width + idxY].getImage(), screenX, screenY);
			}
		}

		if (highlightedTileIdxX >= 0 && highlightedTileIdxX < width
				&& highlightedTileIdxY >= 0 && highlightedTileIdxY < height) {
			int screenX = getScreenX(highlightedTileIdxX, highlightedTileIdxY);
			int screenY = getScreenY(highlightedTileIdxX, highlightedTileIdxY);
			Shape highlightShape = tiles[highlightedTileIdxX * width + highlightedTileIdxY].getHighlightShape();
			highlightShape.setX(screenX);
			highlightShape.setY(screenY);
			g.setColor(Color.orange);
			g.draw(highlightShape);
		}

		// Draw GUI
		for (int idx = 0; idx < guiTiles.length; idx++) {
			g.drawImage(guiTiles[idx].getImage(), 0, idx * guiSize);
			if (highlightedGui >= 0) {
				Shape highlightShape = guiTiles[highlightedGui].getHighlightShape();
				highlightShape.setX(0);
				highlightShape.setY(highlightedGui * guiSize);
				g.setColor(Color.orange);
				g.draw(highlightShape);
			}
		}
	}

	private int getScreenX(int idxX, int idxY) {
		return offsetX - (idxX * tileSizeWidthHalf) + (idxY * tileSizeWidthHalf) - tileSizeWidthHalf;
	}

	private int getScreenY(int idxX, int idxY) {
		return offsetY + (idxX * tileSizeHeightHalf) + (idxY * tileSizeHeightHalf);
	}
}
