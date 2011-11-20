/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simpletest;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author robert
 */
public class Main extends BasicGame {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new Main("Simple Test"));
			app.setDisplayMode(800, 600, false);
			app.setSmoothDeltas(true);
			app.setTargetFrameRate(60);
			app.start();
		} catch (SlickException ex) {
			System.err.print(ex);
			System.exit(0);
		}
	}
	private float fast = 0.2f;
	private float normal = 0.1f;
	private float slow = 0.05f;
	private Wheel wheel;

	public Main(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		wheel = new Wheel();
		wheel.createDots();
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();

		if (input.isKeyDown(Input.KEY_Q)) {
			wheel.rotate(-fast * delta);
		}
		if (input.isKeyDown(Input.KEY_E)) {
			wheel.rotate(fast * delta);
		}

		if (input.isKeyDown(Input.KEY_A)) {
			wheel.rotate(-normal * delta);
		}
		if (input.isKeyDown(Input.KEY_D)) {
			wheel.rotate(normal * delta);
		}

		if (input.isKeyDown(Input.KEY_Y)) {
			wheel.rotate(-slow * delta);
		}
		if (input.isKeyDown(Input.KEY_C)) {
			wheel.rotate(slow * delta);
		}

		if (input.isKeyPressed(Input.KEY_SPACE)) {
			wheel.checkForHits();
		}

		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			gc.exit();
		}
	}

	public void render(GameContainer gc, Graphics grphcs) throws SlickException {
		if(wheel.hasPickedUp()) {
			grphcs.setColor(Color.blue);
		} else {
			grphcs.setColor(Color.red);
		}
		grphcs.drawLine(gc.getWidth() - 5, gc.getHeight() / 2, gc.getWidth() - 175, gc.getHeight() / 2);

		wheel.render(gc, grphcs);
	}
}
