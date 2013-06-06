void setup() {
  size(500, 500);
  background(0);
}

float size = 3f;

void draw() {
  copy(
    0, 0, width, height,
    -10, -10, width + 20, height + 20);
  
  for(int count = 0; count < 25; count++) {
    ellipse(random(0, width), random(0, height), size, size);
  } 
}
