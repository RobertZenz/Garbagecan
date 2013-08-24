import logging

from yapsy.PluginManager import PluginManager

logging.basicConfig()

manager = PluginManager()
manager.setPluginPlaces(["./plugins"])
manager.collectPlugins()

for plugin in manager.getAllPlugins():
	print("Activating: " + plugin.name)
	manager.activatePluginByName(plugin.name)
