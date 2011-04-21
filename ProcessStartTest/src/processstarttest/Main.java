/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processstarttest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author robert
 */
public class Main {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// Passing this in breaks it...
		String command = "bash -c 'mkdir deleteme'"; //args[0];

		System.out.println("Going to run: " + command);

		// Execute a process
		try {
			Process proc = Runtime.getRuntime().exec(new String[] {"bash", "-c", "mkdir deleteme"}); // This works!
			proc.waitFor();

			getStreamOutput("Output", proc.getInputStream());
			getStreamOutput("Error", proc.getErrorStream());
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		} catch (InterruptedException ex) {
			System.err.println(ex.getMessage());
		}
	}

	private static String getStreamOutput(String name, InputStream strm) {
		StringBuilder builder = new StringBuilder();

		String tempLine;
		try {
			System.out.println(name);
			InputStreamReader reader = new InputStreamReader(strm);
			BufferedReader bufReader = new BufferedReader(reader);
			while ((tempLine = bufReader.readLine()) != null) {
				System.out.println(tempLine);
			}
			System.out.println();

			bufReader.close();
			reader.close();
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}

		return builder.toString();
	}
}
