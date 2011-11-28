/*
 * Public Domain
 */
package dragwithtarget;

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
public class Environment extends BasicGame {

	private List<DragItem> items = new ArrayList<DragItem>();
	private List<DragTarget> targets = new ArrayList<DragTarget>();
	private DragItem highlightedItem;
	private DragItem draggedItem;

	public Environment(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		int startY = 150;
		int size = 100;
		int targetSpace = size + Item.BORDER_SIZE;

		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				targets.add(new DragTarget(
						450 + x * targetSpace,
						startY + y * targetSpace,
						size,
						size,
						Color.red));
				items.add(new DragItem(
						50 + x * size,
						startY + y * size,
						size,
						size,
						new Color(0, 255 / 9 * (x * 3 + y), 255)));
			}
		}
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();

		if (draggedItem != null) {
			draggedItem.doDrag(input, targets);
		}
		if (highlightedItem != null && highlightedItem.checkState(input) != State.HIGHLIGHTED) {
			highlightedItem = null;
		}
		if (draggedItem != null && draggedItem.checkState(input) != State.DRAGGED) {
			draggedItem = null;
		}

		if (highlightedItem == null && draggedItem == null) {
			for (DragItem item : items) {
				switch (item.checkState(input)) {
					case DRAGGED:
						draggedItem = item;
						draggedItem.startDrag(input);
						break;
					case HIGHLIGHTED:
						highlightedItem = item;
						break;
				}
			}
		}

		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		for (DragTarget target : targets) {
			target.render(g);
		}
		for (DragItem item : items) {
			item.render(g);
		}

		if (highlightedItem != null) {
			highlightedItem.renderBorder(g, Color.pink);
			highlightedItem.render(g);
		}
		if (draggedItem != null) {
			draggedItem.renderBorder(g, Color.orange);
			draggedItem.render(g);
		}
	}
}
