package plugins.repository;

import plugins.Plugin;

public class Squared implements Plugin {

	@Override
	public String getDescription() {
		return "Retuns the square of the provided number.";
	}

	@Override
	public String getHelp() {
		return "Usage: square <number> [<number>...]";
	}

	@Override
	public String getLongName() {
		return "The Square Plugin";
	}

	@Override
	public String getName() {
		return "Square";
	}

	@Override
	public String getPattern() {
		return "^square.*";
	}

	@Override
	public boolean input(String input) {
		String[] command = input.split(" ");
		
		if (command.length <= 1) {
			return false;
		}
		
		for(int idx = 1; idx < command.length; idx++) {
			if(command[idx].matches("[0-9]+")) {
				System.out.println(command[idx] + "^2 = " + Math.pow(Double.parseDouble(command[idx]), 2));
			} else {
				System.err.println(command[idx] + ": Not a number.");
			}
		}
		
		return true;
	}

}
