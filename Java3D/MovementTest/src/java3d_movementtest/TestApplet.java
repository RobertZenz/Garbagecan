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
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

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
		viewerCenter.x += (e.getX() - mousePoint.x) / 100;
		viewerCenter.y += (e.getY() - mousePoint.y) / 100;

		mousePoint.x = e.getX();
		mousePoint.y = e.getY();

		applyMovement();
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
				viewerEye.x -= 0.1f;
				applyMovement();
				break;

			case KeyEvent.VK_D:
				viewerEye.x += 0.1f;
				applyMovement();
				break;

			case KeyEvent.VK_S:
				viewerEye.z += 0.1f;
				applyMovement();
				break;

			case KeyEvent.VK_W:
				viewerEye.z -= 0.1f;
				applyMovement();
				break;

			case KeyEvent.VK_ESCAPE:
				System.exit(0);
				break;

		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}
	private SimpleUniverse universe;
	private TransformGroup viewTransform;
	private Point3d viewerEye = new Point3d(0f, 0f, 12f);
	private Point3d viewerCenter = new Point3d(0f, 0f, 0f);
	private Vector3d viewerFrustum = new Vector3d(0f, 1f, 0f);
	private Point3d mousePoint = new Point3d();
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

		viewTransform = universe.getViewingPlatform().getMultiTransformGroup().getTransformGroup(0);

		applyMovement();

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
		moveTransform.lookAt(viewerEye, viewerCenter, viewerFrustum);
		moveTransform.invert();
		viewTransform.setTransform(moveTransform);
	}
}
