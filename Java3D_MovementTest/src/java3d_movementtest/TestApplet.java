/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java3d_movementtest;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

/**
 *
 * @author robert
 */
public class TestApplet extends Applet implements KeyListener, MouseListener, MouseMotionListener {

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
		System.out.println("mouseMoved");
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case 65: // A
				viewVector.x -= 0.01f;
				applyMovement();
				break;

			case 68: // D
				viewVector.x += 0.01f;
				applyMovement();
				break;

			case 83: // S
				viewVector.z += 0.01f;
				applyMovement();
				break;

			case 87: // W
				viewVector.z -= 0.01f;
				applyMovement();
				break;

		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}
	private SimpleUniverse universe;
	private TransformGroup viewTransform;
	private Vector3f viewVector = new Vector3f();
	private Canvas3D canvas;
	private MainFrame frame;

	public MainFrame getFrame() {
		return frame;
	}

	public void setFrame(MainFrame frame) {
		this.frame = frame;
	}

	public TestApplet() {
		setLayout(new BorderLayout());
	}

	public void bigBang() {
		System.out.println("BigBang: Creating Objects.");
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		canvas = new Canvas3D(config);
		universe = new SimpleUniverse(canvas);

		System.out.println("BigBang: Wiring Events.");
		wireEvents();

		System.out.println("BigBang: Generating Universe.");
		Generator.generate(universe);

		universe.getViewingPlatform().setNominalViewingTransform();
		viewTransform = universe.getViewingPlatform().getMultiTransformGroup().getTransformGroup(0);
		Transform3D transform3d = new Transform3D();
		viewTransform.getTransform(transform3d);
		transform3d.get(viewVector);

		// Add to the applet
		add("Center", canvas);
	}

	private void wireEvents() {
		canvas.addKeyListener(this);
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);

		frame.addKeyListener(this);
		frame.addMouseListener(this);
		frame.addMouseMotionListener(this);
	}

	private void applyMovement() {
		Transform3D moveTransform = new Transform3D();
		moveTransform.setTranslation(viewVector);
		viewTransform.setTransform(moveTransform);
	}
}
