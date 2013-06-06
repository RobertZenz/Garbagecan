void setup() {
  size(500, 500);
}

float mod = 2f;
float ellipseWidth = 100f;
float ellipseHeight = 100f;

void draw() {
  background(0);
  
  ellipseWidth += random(-mod, mod);
  ellipseHeight += random(-mod, mod);
  
  ellipseWidth = max(ellipseWidth, 0);
  ellipseHeight = max(ellipseHeight, 0);
  
  ellipse(
    width / 2, height / 2,
    ellipseWidth, ellipseHeight);
}
