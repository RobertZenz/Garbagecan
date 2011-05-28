/*
 * Public Domain
 */

/*
 * This follows the tutorials at: http://www.java3d.org/tutorial.html
 */

package java3d_devtest;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.BranchGroup;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SimpleUniverse universe = new SimpleUniverse();
		BranchGroup group = new BranchGroup();

		group.addChild(new ColorCube(0.3));

		universe.getViewingPlatform().setNominalViewingTransform();
		universe.addBranchGraph(group);
    }

}
