from yapsy.IPlugin import IPlugin

class First(IPlugin):
	def activate(pluginManager):
		print("Active")
	
	def deactivate():
		print("dead")
