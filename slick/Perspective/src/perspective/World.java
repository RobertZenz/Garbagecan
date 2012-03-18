/*
 * Public Domain
 */
package perspective;

import java.util.ArrayList;
import java.util.List;
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

	private List<Layer> layers = new ArrayList<Layer>();

	public World(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		layers.add(new Layer(Color.gray, 300, 125, 225));
		layers.add(new Layer(Color.yellow, 400, 150, 200));
		layers.add(new Layer(Color.blue, 400, 150, 125));
		layers.add(new Layer(Color.green, 75, 75, 100));
		layers.add(new Layer(Color.red, 400, 150, 50));
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();

		for(Layer layer : layers) {
			layer.update(container, delta);
		}

		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		for(Layer layer : layers) {
			layer.render(container, g);
		}
	}
}
