/*
 * Public Domain
 */
package movement3;

import java.util.Random;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class World extends BasicGame {

	private Terrain terrain;
	private Player player;
	private int maxWorldLocation;
	private int worldLocation;

	public World(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		int length = container.getWidth() / 8;
		int space = container.getWidth() / 8;

		Random random = new Random(0);

		this.terrain = new Terrain(random, length, space);
		this.player = new Player(5);
		this.maxWorldLocation = length * space;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();

		if (input.isKeyDown(Input.KEY_A)) {
			worldLocation -= delta;
			player.setLocation(player.getLocation() - delta);
			if (worldLocation < 0) {
				worldLocation = 0;
			}
		}
		if (input.isKeyDown(Input.KEY_D)) {
			worldLocation += delta;
			player.setLocation(player.getLocation() + delta);
			if (worldLocation > maxWorldLocation - container.getWidth()) {
				worldLocation = maxWorldLocation - container.getWidth();
			}
		}


		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		g.setBackground(Color.black);
		g.setColor(Color.white);
		for (int idx = worldLocation + 1; idx < worldLocation + container.getWidth(); idx++) {
			g.drawLine(idx - worldLocation - 1,
					terrain.getTerrainHeight(idx - 1) * container.getHeight(),
					idx - worldLocation,
					terrain.getTerrainHeight(idx) * container.getHeight());
		}

		player.render(g, (int) (terrain.getTerrainHeight(player.getLocation() + worldLocation) * container.getHeight()));

		int y = 25;
		g.setColor(Color.lightGray);
		g.drawString("MaxWorldLocation: " + Integer.toString(maxWorldLocation), 5, y);
		g.drawString("WorldLocation: " + Integer.toString(worldLocation), 5, y += 15);
		g.drawString("PlayerLocation: " + Integer.toString(player.getLocation()), 5, y += 15);
	}
}
