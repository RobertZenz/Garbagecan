/*
 * Public Domain
 */

package worldtest;

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
        WorldApplet applet = new WorldApplet();
		applet.setFrame(new MainFrame(applet, args, 1024, 768));
		applet.getFrame().setTitle("OpenWiggles World Test");
		applet.bigBang();
		applet.validate();
    }

}
