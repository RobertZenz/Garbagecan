/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simpletest;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author robert
 */
public class Dot {

	private float atAngle;
	private float fromBorder;
	private int radius = 6;
	private boolean isReceiver;
	private boolean hasContent;

	public boolean hasContent() {
		return hasContent;
	}

	public void setHasContent(boolean hasContent) {
		this.hasContent = hasContent;
	}

	public boolean isReceiver() {
		return isReceiver;
	}

	public void setIsReceiver(boolean isReceiver) {
		this.isReceiver = isReceiver;
	}

	public float getAtAngle() {
		return atAngle;
	}

	public void setAtAngle(float atAngle) {
		this.atAngle = Helper.sanitizeAngle(atAngle);
	}

	public Dot(float position, boolean isReceiver) {
		this.atAngle = Helper.sanitizeAngle(position);
		this.isReceiver = isReceiver;

		if (isReceiver) {
			this.fromBorder = -25;
		}
	}

	public boolean isHit(float parentAngle) {
		float position = Helper.sanitizeAngle(atAngle + parentAngle);
		return (position <= 3 || position >= 357);
	}

	public void render(GameContainer gc, Graphics grphcs, Wheel parent) throws SlickException {
		float centerX = gc.getWidth() / 2;
		float centerY = gc.getHeight() / 2;

		float targetRotation = Helper.sanitizeAngle(atAngle + parent.getRotation());

		float parentRadius = parent.getDiameter() / 2 + fromBorder;

		float targetX = centerX + parentRadius * (float) Math.cos(Math.toRadians(targetRotation));
		float targetY = centerY + parentRadius * (float) Math.sin(Math.toRadians(targetRotation));

		if (isReceiver) {
			if (hasContent) {
				grphcs.setColor(org.newdawn.slick.Color.green);
			} else {
				grphcs.setColor(org.newdawn.slick.Color.blue);
			}
		} else {
			grphcs.setColor(org.newdawn.slick.Color.red);
		}
		grphcs.fillOval(targetX - radius, targetY - radius, radius * 2, radius * 2);
	}
}
