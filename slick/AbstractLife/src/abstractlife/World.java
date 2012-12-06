/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractlife;

import il.ac.idc.jdt.Triangle;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class World extends BasicGame {

	private PetriDish dish;

	public World(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		dish = new PetriDish();
		dish.init(0, 100, container.getWidth(), container.getHeight());
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();

		if (input.isMousePressed(0)) {
			Cell cell = dish.getCellAt(input.getMouseX(), input.getMouseY());
			if (cell != null) {
				cell.setLiving(true);
			}
		}
		if (input.isMousePressed(1)) {
			Cell cell = dish.getCellAt(input.getMouseX(), input.getMouseY());
			if (cell != null) {
				cell.setLiving(false);
			}
		}
		if (input.isKeyDown(Input.KEY_SPACE) || input.isKeyPressed(Input.KEY_RETURN)) {
			dish.runGeneration();
		}

		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		g.setBackground(Color.white);

		for (Cell cell : dish.getCells()) {
			if (cell.isLiving()) {
				g.setColor(Color.black);
				fillTriangle(g, cell);
			}
		}
		g.setColor(Color.lightGray);
		for (Cell cell : dish.getCells()) {
			drawTriangle(g, cell);
		}
	}

	private static void drawTriangle(Graphics g, Triangle triangle) {
		g.drawLine((float) triangle.getA().getX(), (float) triangle.getA().getY(), (float) triangle.getB().getX(), (float) triangle.getB().getY());
		g.drawLine((float) triangle.getB().getX(), (float) triangle.getB().getY(), (float) triangle.getC().getX(), (float) triangle.getC().getY());
		g.drawLine((float) triangle.getC().getX(), (float) triangle.getC().getY(), (float) triangle.getA().getX(), (float) triangle.getA().getY());
	}

	private static void fillTriangle(Graphics g, Triangle triangle) {
		Polygon polygon = new Polygon(new float[]{
					(float) triangle.getA().getX(),
					(float) triangle.getA().getY(),
					(float) triangle.getB().getX(),
					(float) triangle.getB().getY(),
					(float) triangle.getC().getX(),
					(float) triangle.getC().getY()
				});
		polygon.setClosed(true);

		g.fill(polygon);
	}
}
