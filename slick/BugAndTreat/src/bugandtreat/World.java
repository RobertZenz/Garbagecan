/*
 * Public Domain
 */
package bugandtreat;

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

	private int width;
	private int height;
	private List<Bug> bugs = new ArrayList<Bug>();
	private List<Treat> treats = new ArrayList<Treat>();

	public World(String title) {
		super(title);
	}

	public int getHeight() {
		return height;
	}

	public Iterable<Treat> getTreats() {
		return treats;
	}

	public int getWidth() {
		return width;
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		width = container.getWidth();
		height = container.getHeight();

		bugs.add(new Bug(0, this, Color.red, 75));
		bugs.add(new Bug(1, this, Color.blue, 100));
		bugs.add(new Bug(2, this, Color.pink, 25));
		bugs.add(new Bug(3, this, Color.orange, 85));
		bugs.add(new Bug(4, this, Color.cyan, 45));
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		for (Bug bug : bugs) {
			bug.update();
		}

		Input input = container.getInput();

		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			treats.add(new Treat(input.getMouseX(), input.getMouseY()));
		}
		if (input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON)) {
			treats.add(new Treat(input.getMouseX(), input.getMouseY()));
		}

		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}
	}

	public void removeTreat(Treat treat) {
		treats.remove(treat);
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		for (Treat treat : treats) {
			treat.render(g);
		}

		for (Bug bug : bugs) {
			bug.render(g);
		}

		// Draw debug strings
		int x = 5;
		int y = 10;
		int textHeight = 15;

		g.setColor(Color.darkGray);
		g.drawString("Treats " + Integer.toString(treats.size()), x, y += textHeight);
	}

	public boolean treatStillExists(Treat treat) {
		return treats.contains(treat);
	}
}
