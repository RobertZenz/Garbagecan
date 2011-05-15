import plugins.Manager;


public class Main {
	public static void main(String[] args) {
		System.out.println("Plugin Architecture and Eclipse Test.");
		System.out.println();
		
		Manager manager = new Manager();
		System.out.println();
		
		manager.run("hello My sweetheart");
	}
}
