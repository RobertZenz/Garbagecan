package plugins;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class Manager {
	private String repository = "plugins.repository";
	private List<Plugin> plugins = new ArrayList<Plugin>();
	
	public Manager() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		
		try {
			Enumeration<URL> resources = loader.getResources(repository.replace(".", "/"));
			
			while (resources.hasMoreElements()) {
				URL resource = resources.nextElement();
				File dir = new File(resource.getPath());
				
				for (File file : dir.listFiles()) {
					String classname = repository + "." + file.getName().replaceAll(".class", "");
					Class<?> item = Class.forName(classname);
					Object instance = item.newInstance();
					
					if (instance instanceof Plugin) {
						plugins.add((Plugin) instance);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Loaded PlugIns:");
		for (Plugin plugin : plugins) {
			System.out.println(plugin.getName());
		}
	}

	public void run(String input) {
		for (Plugin plugin : plugins) {
			if(input.matches(plugin.getPattern())) {
				if(!plugin.input(input)) {
					System.err.println(plugin.getHelp());
				}
			}
		}
	}
}
