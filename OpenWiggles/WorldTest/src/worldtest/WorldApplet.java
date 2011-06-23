/*
 * Public Domain
 */
package worldtest;

import com.sun.j3d.utils.universe.SimpleUniverse;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.J3DGraphics2D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class WorldApplet extends Applet implements KeyListener {

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
				viewerCenter.y += step;
				relocateViewer();
				break;

			case KeyEvent.VK_S:
				viewerCenter.y -= step;
				relocateViewer();
				break;

			case KeyEvent.VK_A:
				viewerCenter.x -= step;
				relocateViewer();
				break;

			case KeyEvent.VK_D:
				viewerCenter.x += step;
				relocateViewer();
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
	private Canvas3D canvas;
	private Frame frame;
	private SimpleUniverse universe;
	private Point3d viewerEye = new Point3d(0, 0, -10);
	private Point3d viewerCenter = new Point3d(0, 0, 50);
	private Vector3d viewerUp = new Vector3d(0, 1, 0);
	private TransformGroup viewerTransformGroup;
	private Transform3D viewerTransform;
	private double step = 0.10;

	public Canvas3D getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas3D canvas) {
		this.canvas = canvas;
	}

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public SimpleUniverse getUniverse() {
		return universe;
	}

	public void setUniverse(SimpleUniverse universe) {
		this.universe = universe;
	}

	public WorldApplet() {
		setLayout(new BorderLayout());
	}

	public void bigBang() {
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		canvas = new Canvas3D(config) {

			@Override
			public void postRender() {
				J3DGraphics2D graphics = getGraphics2D();
				graphics.setColor(Color.WHITE);
				graphics.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 12));
				graphics.drawString("ViewerEye: " + viewerEye.toString(), 5, 25);
				graphics.drawString("ViewerCenter: " + viewerCenter.toString(), 5, 40);
				graphics.flush(false);
			}
		};
		universe = new SimpleUniverse(canvas);

		WorldGenerator.generate(universe);

		viewerTransformGroup = universe.getViewingPlatform().getMultiTransformGroup().getTransformGroup(0);

		add("Center", canvas);

		registerEvents();
		relocateViewer();
	}

	public void registerEvents() {
		canvas.addKeyListener(this);

		frame.addKeyListener(this);
	}

	public void relocateViewer() {
		if (viewerTransform == null) {
			viewerTransform = new Transform3D();
		}

		viewerEye.x = viewerCenter.x;
		viewerEye.y = viewerCenter.y;

		viewerTransform.lookAt(viewerEye, viewerCenter, viewerUp);

		// Okay, this is important, do not forget it in the future!
		viewerTransform.invert();

		viewerTransformGroup.setTransform(viewerTransform);
	}
}
