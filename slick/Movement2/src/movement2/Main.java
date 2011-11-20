/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package movement2;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
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
	    AppGameContainer app = new AppGameContainer(new Main("World 2"));
	    app.setShowFPS(true);
	    app.setDisplayMode(800, 600, false);
	    app.setSmoothDeltas(true);
	    app.setTargetFrameRate(60);
	    app.start();
	} catch (SlickException e) {
	    System.err.print(e);
	    System.exit(0);
	}
    }
    private float speed = 0.4f;
    private World world;

    public Main(String title) {
	super(title);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
	world = new World();
	world.getHero().setX(gc.getWidth() / 2);
	world.getHero().setY(gc.getHeight() / 2);
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
	Input input = gc.getInput();

	float deltaSpeed = speed * delta;

	if (input.isKeyDown(Input.KEY_ESCAPE)) {
	    gc.exit();
	}

	if (input.isKeyDown(Input.KEY_W)) {
	    world.move(0, -deltaSpeed, gc.getWidth(), gc.getHeight());
	}
	if (input.isKeyDown(Input.KEY_S)) {
	    world.move(0, +deltaSpeed, gc.getWidth(), gc.getHeight());
	}
	if (input.isKeyDown(Input.KEY_A)) {
	    world.move(-deltaSpeed, 0, gc.getWidth(), gc.getHeight());
	}
	if (input.isKeyDown(Input.KEY_D)) {
	    world.move(+deltaSpeed, 0, gc.getWidth(), gc.getHeight());
	}
    }

    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
	world.render(gc, grphcs);

	grphcs.drawLine(gc.getWidth() / 2, 0, gc.getWidth() / 2, gc.getHeight());
	grphcs.drawLine(0, gc.getHeight() / 2, gc.getWidth(), gc.getHeight() / 2);


	grphcs.drawString(Float.toString(world.getX()), 5, 25);
	grphcs.drawString(Float.toString(world.getX()), 5, 40);
    }
}
