/*
 * Public Domain
 */

/*
 * This follows the tutorials at: http://www.java3d.org/tutorial.html
 */

package java3d_devtest;

import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.Color;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

		// The universe...no, the answer is ot 42!
        SimpleUniverse universe = new SimpleUniverse();

		// A group to hold objects.
		BranchGroup group = new BranchGroup();

		// We're adding a sphere.
		group.addChild(new Sphere(0.5f));

		// The light source
		DirectionalLight blueLight = new DirectionalLight(new Color3f(Color.BLUE), new Vector3f(4.0f, -7.0f, -12.0f));

		// The bounds for the light source.
		BoundingSphere bounds = new BoundingSphere(new Point3d(0, 0, 0), 100);

		// Make the light source a spherical one.
		blueLight.setInfluencingBounds(bounds);

		// Add the light source.
		group.addChild(blueLight);

		// Set the viewpoint to...something...
		universe.getViewingPlatform().setNominalViewingTransform();

		// Add the group to the universe.
		universe.addBranchGraph(group);
    }

}
