/*
 * Public Domain
 */
package dragwithtarget;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class DragItem extends Item {

	private int offsetX;
	private int offsetY;

	public DragItem(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
	}

	public State checkState(Input input) {
		boolean isMouseWithin = isWithin(input.getMouseX(), input.getAbsoluteMouseY());
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && isMouseWithin) {
			return State.DRAGGED;
		} else if (isMouseWithin) {
			return State.HIGHLIGHTED;
		} else {
			return State.NORMAL;
		}
	}

	public void startDrag(Input input) {
		offsetX = x - input.getMouseX();
		offsetY = y - input.getMouseY();
		x = input.getMouseX() + offsetX;
		y = input.getMouseY() + offsetY;
	}

	public void doDrag(Input input, Iterable<DragTarget> targets) {
		boolean isInTarget = false;

		for (DragTarget target : targets) {
			if (target.isWithin(input.getMouseX(), input.getMouseY())) {
				x = target.x;
				y = target.y;
				isInTarget = true;
				break;
			}
		}
		if (!isInTarget) {
			x = input.getMouseX() + offsetX;
			y = input.getMouseY() + offsetY;
		}
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);

		g.setColor(Color.black);
		g.drawString(Integer.toString(color.getGreen()), x, y);
	}
}
