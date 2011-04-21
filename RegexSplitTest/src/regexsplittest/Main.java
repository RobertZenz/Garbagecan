/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package regexsplittest;

import java.util.regex.Pattern;

/**
 *
 * @author robert
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String needsSplitting = "bash -c 'tar -zcvf ~/server/backups/server/$(date \"+%d-%m-%Y_%H:%M:%S\").tar.gz ~/server/* --exclude \"~/server/backups/*\" --exclude \"~/server/world/*\" --exclude \"~/server/server.l*\" --exclude \"~/server/logs/worldedit/worldedit.log.*\" --exclude \"~/server/plugins/CommandBook/bans.*\" --exclude \"~/server/plugins/dynmap/web/tiles/*\"'";

		Pattern regex = Pattern.compile("(?<=^[^']*(?:'[^']?'[^']?)?) (?=(?:[^']*'[^']*')*[^']*$)");
		
		for (String group : regex.split(needsSplitting)) {
			System.out.println(group);
		}
    }

}
