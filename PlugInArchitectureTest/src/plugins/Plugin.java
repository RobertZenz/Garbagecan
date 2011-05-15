package plugins;

public interface Plugin {
	public String getName();
	public String getLongName();
	public String getDescription();
	public String getHelp();
	public String getPattern();
	
	public boolean input(String input);
}
