
extends Panel

# member variables here, example:
# var a=2
# var b="textvar"

func _ready():
	get_node("Button").connect("pressed", self, "_on_benice_pressed");

func _on_benice_pressed():
	get_node("Label").set_text("Hello World!");
