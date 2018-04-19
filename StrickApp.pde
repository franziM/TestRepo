float posYHeader;
float posYOverview;
float posYPattern;
float posYBottom;

int numberStitchesX;
int numberStitchesY;

float sizeStitch;
float widthPattern;

boolean overPlusX;
boolean overMinusX;

void setup()
{
  size(300, 500);
  noStroke();
  
  posYHeader = height*0.05;
  posYOverview = height*0.2;
  posYPattern = height*0.8;
  posYBottom = height;
  
  numberStitchesX = 12;
  numberStitchesY = 12;
  
  widthPattern = width*0.8;
  sizeStitch = widthPattern/numberStitchesX;
 
}

void draw()
{
  widthPattern = width*0.8;
  sizeStitch = widthPattern/numberStitchesX;
  
  Update(mouseX, mouseY);
  
  noStroke();
  // Divide screen into three parts.
  fill(100);
  rect(0, 0, width, posYHeader);
  fill(60);
  rect(0, posYHeader, width, posYOverview);
  fill(120);
  rect(0, posYOverview, width, posYPattern);  
  fill(80);
  rect(0, posYPattern, width, posYBottom); 
  
  // Draw header.
  fill(60);
  textSize(30);
  text("StrickLiesel 0.1", 10, posYHeader);
  
  // Draw grid of stitches
  drawStitchGrid(numberStitchesX, numberStitchesY);
}

void drawStitchGrid(int nrX, int nrY)
{
  stroke(120);
  strokeWeight(2);
  
  pushMatrix();
  translate(width*0.1, posYOverview + width*0.1);
  for(int i = 0; i < nrX; i++)
  {
      for(int j = 0; j < nrX; j++)
      {
        rect(j*sizeStitch, i*sizeStitch, sizeStitch, sizeStitch);
      } 
  }
  
  popMatrix();
  
  // Two Buttons
  fill(255, 0, 0);
  ellipse(width*0.1 + 10, posYOverview + width*0.1, 20, 20);
  
  fill(0, 255, 255);
  ellipse(widthPattern, posYOverview + width*0.1, 20, 20);
}

void mousePressed() {
  if (overPlusX) {
    numberStitchesX++;
  }
  if (overMinusX) {
    numberStitchesX--;
  }
}

boolean overCircle(float x, float y, float diameter) {
  float disX = x - mouseX;
  float disY = y - mouseY;
  if (sqrt(sq(disX) + sq(disY)) < diameter/2 ) {
    return true;
  } else {
    return false;
  }
}

void Update(int x, int y) {
  if ( overCircle(width*0.1 + 10, posYOverview + width*0.1, 20.0f) )
  {
    overPlusX = true;
  }
  else
  {
    overPlusX = false;
  }
  
  if ( overCircle(widthPattern, posYOverview + width*0.1, 20.0f) )
  {
    overMinusX = true;
  }
  else
  {
    overMinusX = false;
  }
}
