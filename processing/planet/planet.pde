void setup() {
  size(500, 500);
  rectMode(CENTER);
  noStroke();
}

float sunSize = 75f;
float planetSize = 30f;
float angle = 0;
float angleChange = 0.075f;
float radius = 125f;
float radiusMod = 0.125f;

void draw() {
  background(0);
  
  angle += angleChange;
  if(angle > 360) {
    angle -= 360;
  }

  radius += random(-radiusMod, radiusMod);
  
  float x = cos(angle) * radius + width / 2;
  float y = sin(angle) * radius + height / 2;
  
  ellipse(x, y, planetSize, planetSize);
  
  fill(250, 218, 100);
  ellipse(
    width / 2, height / 2,
    sunSize, sunSize);
}
