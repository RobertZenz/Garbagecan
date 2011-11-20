/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simpletest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author robert
 */
public class Wheel {

	private Image wheel;
	private float rotation = 0;
	private List<Dot> dots = new ArrayList<Dot>();
	private List<Dot> targets = new ArrayList<Dot>();
	private Dot pickedUp;
	private double totalDone;

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public float getDiameter() {
		return wheel.getWidth();
	}

	public boolean hasPickedUp() {
		return pickedUp != null;
	}

	public Wheel() throws SlickException {
		this.wheel = new Image("data/wheel.png");
	}

	public void createDots() {
		dots.clear();
		targets.clear();

		Random random = new Random(System.currentTimeMillis());
		for (int count = 0; count <= random.nextInt(50); count++) {
			dots.add(new Dot(random.nextInt(359), false));
			targets.add(new Dot(random.nextInt(359), true));
		}
	}

	public void rotate(float angle) {
		wheel.setRotation(Helper.sanitizeAngle(rotation += angle));
	}

	public void checkForHits() {
		if (pickedUp == null) {
			for (Dot dot : dots) {
				if (dot.isHit(rotation)) {
					pickedUp = dot;
					break;
				}
			}
			if (pickedUp != null) {
				dots.remove(pickedUp);
			}
		} else {
			for (Dot target : targets) {
				if (!target.hasContent() && target.isHit(rotation)) {
					totalDone++;
					target.setHasContent(true);
					pickedUp = null;
					break;
				}
			}
			if (pickedUp != null) {
				pickedUp.setAtAngle(0 - rotation);
				dots.add(pickedUp);
				pickedUp = null;
			}
		}

		boolean allDone = true;
		for (Dot target : targets) {
			allDone &= target.hasContent();
		}

		if (allDone) {
			createDots();
		}
	}

	public void render(GameContainer gc, Graphics grphcs) throws SlickException {
		grphcs.drawImage(wheel,
				(gc.getWidth() - wheel.getWidth()) / 2,
				(gc.getHeight() - wheel.getHeight()) / 2);

		for (Dot dot : dots) {
			dot.render(gc, grphcs, this);
		}
		for (Dot target : targets) {
			target.render(gc, grphcs, this);
		}

		grphcs.setColor(Color.white);
		grphcs.drawString("Score: " + Integer.toString((int) totalDone), 10, 25);
	}
}
