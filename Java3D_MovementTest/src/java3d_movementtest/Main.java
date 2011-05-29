/*
 * Public Domain
 */

package java3d_movementtest;

import com.sun.j3d.utils.applet.MainFrame;

/**
 *
 * @author Robert 'Bobby' Zenz
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TestApplet applet = new TestApplet();
		applet.setFrame(new MainFrame(applet, args, 250, 250));
		applet.bigBang();
		applet.validate();
    }

}
