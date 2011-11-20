/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package simpletest;

/**
 *
 * @author robert
 */
public final class Helper {
    private static float _max = 360;
    private static float _min = 0;

    public static float sanitizeAngle(float angle) {
	while(angle >= _max) {
	    angle -= _max;
	}
	while(angle < _min) {
	    angle += _max;
	}

	return angle;
    }
}
