/*
 * Public Domain
 */
package dragging;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Main environment
 * @author Robert 'Bobby' Zenz
 */
public class Environment extends BasicGame {

	/**
	 * List of the dragable items.
	 */
	private List<DragItem> items = new ArrayList<DragItem>();

	public Environment(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		items.add(new DragItem(30, 30, 50, 60, Color.yellow));
		items.add(new DragItem(400, 200, 80, 30, Color.red));
		items.add(new DragItem(700, 500, 100, 100, Color.blue));
		items.add(new DragItem(250, 250, 40, 30, Color.green));
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		for (DragItem item : items) {
			item.update(container, delta);
		}

		Input input = container.getInput();

		// Exit?
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		for (DragItem item : items) {
			item.render(container, g);
		}
	}
}
