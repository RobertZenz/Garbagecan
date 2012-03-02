/*
 * Public Domain
 */
package digging.entities;

import digging.Entity;
import digging.Tile;
import digging.World;
import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Digger extends Entity {

	private static final float SPEED_HORIZONTAL = 0.3f;
	private static final float SPEED_VERTICAL = 0.1f;
	private static final int SIZE = 10;
	private Random random = new Random(0);
	private Vector2f movement = new Vector2f(0, 0);

	public Digger(World world, float locationX, float locationY) {
		this.world = world;
		this.locationX = locationX;
		this.locationY = locationY;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		float newX = movement.getX() * SPEED_HORIZONTAL * delta;
		float newY = movement.getY() * SPEED_VERTICAL * delta;

		float tileX = locationX / Tile.SIZE;
		float tileY = locationY / Tile.SIZE;
		Tile tile = world.getTile((int) (tileX), (int) (tileY));

		

		locationX += newX;
		locationY += newY;

		if (random.nextInt(255) > 200) {
			movement.set(random.nextFloat(), random.nextFloat());
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.setColor(Color.green);
		g.fillOval(locationX, locationY, SIZE, SIZE);
	}
}
