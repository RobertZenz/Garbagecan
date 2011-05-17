import plugins.Manager;


public class Main {
	public static void main(String[] args) {
		System.out.println("Plugin Architecture and Eclipse Test.");
		System.out.println();
		
		Manager manager = new Manager();
		System.out.println();
		
		
		// These work.
		manager.run("hello My sweetheart");
		manager.run("hello Second");
		manager.run("square 2 9 512");
		
		// These fail.
		manager.run("HElo test");
		manager.run("square accd");
		manager.run("hello");
	}
}
