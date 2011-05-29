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

/**
 *
 * @author robert
 */
public class TestApplet extends Applet implements KeyListener, MouseListener, MouseMotionListener {

	public void mouseClicked(MouseEvent e) {
		System.out.println("mouseClicked");
	}

	public void mouseEntered(MouseEvent e) {
		System.out.println("mouseEntered");
	}

	public void mouseExited(MouseEvent e) {
		System.out.println("mouseExited");
	}

	public void mousePressed(MouseEvent e) {
		System.out.println("mousePressed");
	}

	public void mouseReleased(MouseEvent e) {
		System.out.println("mouseReleased");
	}

	public void mouseDragged(MouseEvent e) {
		System.out.println("mouseDragged");
	}

	public void mouseMoved(MouseEvent e) {
		System.out.println("mouseMoved");
	}

	public void keyPressed(KeyEvent e) {
		System.out.println("keyPressed");
	}

	public void keyReleased(KeyEvent e) {
		System.out.println("keyRelease");
	}

	public void keyTyped(KeyEvent e) {
		System.out.println("keyTyped");
	}

	private Canvas3D canvas;
	private MainFrame frame;

	public MainFrame getFrame() {
		return frame;
	}

	public void setFrame(MainFrame frame) {
		this.frame = frame;
	}
	private SimpleUniverse universe;


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
}
