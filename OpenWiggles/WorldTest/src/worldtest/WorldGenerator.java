/*
 * Public Domain
 */
package worldtest;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.Color;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class WorldGenerator {

	private static double width = 25;
	private static double height = 25;
	private static Appearance appearance;

	public static void generate(SimpleUniverse universe) {
		if (appearance == null) {
			Color3f ambientColor = new Color3f(Color.LIGHT_GRAY);
			Color3f emissiveColor = new Color3f(Color.BLACK);
			Color3f specularColor = new Color3f(Color.WHITE);
			Color3f diffuseColor = new Color3f();

			appearance = new Appearance();
			appearance.setMaterial(new Material(ambientColor, emissiveColor, specularColor, diffuseColor, 20));
		}

		BranchGroup group = new BranchGroup();

		generateTerrain(group);
		generateLight(group);
		universe.addBranchGraph(group);
	}

	public static void generateTerrain(BranchGroup group) {
		for (float x = 0; x < width; x++) {
			for (float y = 0; y < height; y++) {
				Box box = new Box(1, 1, 1, appearance);
				TransformGroup transformGroup = new TransformGroup();
				Transform3D transform = new Transform3D();
				transform.setTranslation(new Vector3f(x, y, 0));
				transformGroup.setTransform(transform);
				transformGroup.addChild(box);
				group.addChild(transformGroup);
			}
		}
	}

	public static void generateLight(BranchGroup group) {
		DirectionalLight light = new DirectionalLight(new Color3f(Color.WHITE), new Vector3f(1, 1, 1));
		BoundingSphere bounds = new BoundingSphere(new Point3d(25, 25, 25), 50);
		light.setBounds(bounds);
		group.addChild(light);
	}
}
