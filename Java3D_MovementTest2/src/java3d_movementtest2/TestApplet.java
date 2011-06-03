/*
 * Public Domain
 */
package java3d_movementtest2;

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
 * @author Robert 'Bobby' Zenz
 */
public class TestApplet extends Applet implements KeyListener, MouseListener, MouseMotionListener {

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
				break;

			case KeyEvent.VK_A:
				viewerEye.z -= speed;
				move();
				break;

			case KeyEvent.VK_D:
				viewerEye.z += speed;
				move();
				break;

			case KeyEvent.VK_W:
				viewerEye.x+= speed;
				move();
				break;

			case KeyEvent.VK_S:
				viewerEye.x -= speed;
				move();
				break;

		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

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
	}
	private MainFrame frame;

	public MainFrame getFrame() {
		return frame;
	}

	public void setFrame(MainFrame frame) {
		this.frame = frame;
	}
	private double speed = 0.1;
	private Canvas3D canvas;
	private SimpleUniverse universe;
	private Point3d viewerEye = new Point3d(0, 0, 0);
	private Point3d viewerCenter = new Point3d(10, 0, 0);
	private Vector3d viewerUp = new Vector3d(0, 1, 0);
	private TransformGroup viewerTransform;

	public TestApplet() {
		setLayout(new BorderLayout());
	}

	public void bigBang() {
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		canvas = new Canvas3D(config);
		universe = new SimpleUniverse(canvas);

		Generator.generate(universe);

		viewerTransform = universe.getViewingPlatform().getMultiTransformGroup().getTransformGroup(0);

		move();

		registerEvents();

		add("Center", canvas);
	}

	private void registerEvents() {
		canvas.addKeyListener(this);
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);

		frame.addKeyListener(this);
		frame.addMouseListener(this);
		frame.addMouseMotionListener(this);
	}

	private void move() {
		Transform3D transform = new Transform3D();
		transform.lookAt(viewerEye, viewerCenter, viewerUp);
		transform.invert(); // This is important...I think.
		viewerTransform.setTransform(transform);
	}
}
