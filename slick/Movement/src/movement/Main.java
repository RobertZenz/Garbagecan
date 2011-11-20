/*
 * Public Domain
 */
package movement;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Main extends BasicGame {

	private static float _speed = 0.2f;
	private Entity hero;

	public Main(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		hero = new Entity("data/");
		hero.setPrintDebug(true);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();

		if (input.isKeyDown(Input.KEY_W)) {
			hero.setY(hero.getY() - _speed * delta);
		}

		if (input.isKeyDown(Input.KEY_S)) {
			hero.setY(hero.getY() + _speed * delta);
		}

		if (input.isKeyDown(Input.KEY_A)) {
			hero.setX(hero.getX() - _speed * delta);
		}

		if (input.isKeyDown(Input.KEY_D)) {
			hero.setX(hero.getX() + _speed * delta);
		}
	}

	public void render(GameContainer gc, Graphics grphcs) throws SlickException {
		hero.draw(grphcs);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new Main("World Test"));
			app.setDisplayMode(800, 600, false);
			app.setSmoothDeltas(true);
			app.setTargetFrameRate(60);
			app.setShowFPS(true);
			app.start();
		} catch (SlickException e) {
			System.err.print(e);
			System.exit(0);
		}
	}
}
