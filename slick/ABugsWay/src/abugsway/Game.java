/*
 * Public Domain
 */
package abugsway;

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
public class Game extends BasicGame {

	private Bug bug;
	private List<WayPoint> wayPoints = new ArrayList<WayPoint>();

	public Game(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		bug = new Bug();
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if (!wayPoints.isEmpty()) {
			if (bug.isOnTarget()) {
				if (bug.isOnTarget(wayPoints.get(0).getX(), wayPoints.get(0).getY())) {
					wayPoints.remove(0);
				}
				if (!wayPoints.isEmpty()) {
					bug.setTarget(wayPoints.get(0).getX(), wayPoints.get(0).getY());
				}
			}
		}
		bug.update(delta);

		Input input = container.getInput();

		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			wayPoints.add(new WayPoint(input.getMouseX(), input.getMouseY()));
		}

		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		if (!wayPoints.isEmpty()) {
			g.setColor(Color.orange);
			g.drawLine(bug.getX(), bug.getY(), wayPoints.get(0).getX(), wayPoints.get(0).getY());
		}

		bug.render(container, g);

		WayPoint lastPoint = null;
		for (WayPoint wayPoint : wayPoints) {
			if (lastPoint != null) {
				g.setColor(Color.yellow);
				g.drawLine(lastPoint.getX(), lastPoint.getY(), wayPoint.getX(), wayPoint.getY());
			}
			wayPoint.render(container, g);
			lastPoint = wayPoint;
		}
	}
}
