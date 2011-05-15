package plugins.repository;

import plugins.Plugin;

public class HelloRepeater implements Plugin {

	@Override
	public String getDescription() {
		return "Returns whatever you give it.";
	}

	@Override
	public String getHelp() {
		return "Usage: hello <input>";
	}

	@Override
	public String getLongName() {
		return "The Hello Repeater PlugIn";
	}

	@Override
	public String getName() {
		return "HelloRepeater";
	}

	@Override
	public boolean input(String input) {
		String[] command = input.split(" ");
		
		if (command.length <= 1) {
			return false;
		}
		
		String echo = "";
		for (String item : command) {
			echo += item + " ";
		}
		
		System.out.println("Hello World! " + echo);
		
		return true;
	}

	@Override
	public String getPattern() {
		return "^hello.*";
	}
	
}
