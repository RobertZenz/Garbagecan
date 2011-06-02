/*
 * Public Domain
 */
package java3d_movementtest;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.Color;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Generator {
	private static int cubes = 10;

	private Generator() {
	}

	public static void generate(SimpleUniverse universe) {
		BranchGroup group = new BranchGroup();

		//      y+
		//      ^
		//      |
		//      |
		//      |
		//      +--------> x+
		//     /
		//    /
		//   /
		//  V z+

		for (double x = 0; x < cubes; x++) {
			for (double y = 0; y < cubes; y++) {
				for (double z = 0; z < cubes; z++) {
					addCube(group, x, y, z);
				}
			}
		}

		addLight(group);

		universe.addBranchGraph(group);
	}

	private static void addCube(BranchGroup group, double x, double y, double z) {
		ColorCube cube = new ColorCube(0.2);

		TransformGroup transformGroup = new TransformGroup();
		Transform3D transform = new Transform3D();
		Vector3d vector = new Vector3d(x, y, z);

		transformGroup.setTransform(transform);
		transformGroup.addChild(cube);

		group.addChild(transformGroup);
	}

	private static void addLight(BranchGroup group) {
		DirectionalLight light = new DirectionalLight(new Color3f(Color.BLUE), new Vector3f(5.0f, 5.0f, 0.0f));
		BoundingSphere bounds = new BoundingSphere(new Point3d(0, 0, 0), 100.0);
		light.setBounds(bounds);

		group.addChild(light);
	}
}
