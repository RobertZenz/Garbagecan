/*
 * Public Domain
 */
package java3d_movementtest2;

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

	public static void generate(SimpleUniverse universe) {
		BranchGroup branchGroup = new BranchGroup();
		generateCubes(branchGroup);
		generateLight(branchGroup);
		universe.addBranchGraph(branchGroup);
	}
	
	public static void generateCubes(BranchGroup branchGroup) {
		int cubeCount = 10;

		for (double x = 0; x < cubeCount; x++) {
			for (double y = 0; y < cubeCount; y++) {
				for (double z = 0; z < cubeCount; z++) {
					ColorCube cube = new ColorCube(0.2);
					TransformGroup transformGroup = new TransformGroup();
					Transform3D transform = new Transform3D();
					transform.setTranslation(new Vector3d(x, y, z));
					transformGroup.setTransform(transform);
					transformGroup.addChild(cube);
					branchGroup.addChild(transformGroup);
				}
			}
		}
	}

	public static void generateLight(BranchGroup branchGroup) {
		DirectionalLight light = new DirectionalLight(new Color3f(Color.WHITE), new Vector3f(5, 7, 2));
		BoundingSphere bounds = new BoundingSphere(new Point3d(0, 0, 0), 100);
		branchGroup.addChild(light);
	}
}
