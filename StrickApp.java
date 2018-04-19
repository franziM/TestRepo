import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class StrickApp extends PApplet {

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

public void setup()
{
  
  noStroke();
  
  posYHeader = height*0.05f;
  posYOverview = height*0.2f;
  posYPattern = height*0.8f;
  posYBottom = height;
  
  numberStitchesX = 12;
  numberStitchesY = 12;
  
  widthPattern = width*0.8f;
  sizeStitch = widthPattern/numberStitchesX;
 
}

public void draw()
{
  widthPattern = width*0.8f;
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

public void drawStitchGrid(int nrX, int nrY)
{
  stroke(120);
  strokeWeight(2);
  
  pushMatrix();
  translate(width*0.1f, posYOverview + width*0.1f);
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
  ellipse(width*0.1f + 10, posYOverview + width*0.1f, 20, 20);
  
  fill(0, 255, 255);
  ellipse(widthPattern, posYOverview + width*0.1f, 20, 20);
}

public void mousePressed() {
  if (overPlusX) {
    numberStitchesX++;
  }
  if (overMinusX) {
    numberStitchesX--;
  }
}

public boolean overCircle(float x, float y, float diameter) {
  float disX = x - mouseX;
  float disY = y - mouseY;
  if (sqrt(sq(disX) + sq(disY)) < diameter/2 ) {
    return true;
  } else {
    return false;
  }
}

public void Update(int x, int y) {
  if ( overCircle(width*0.1f + 10, posYOverview + width*0.1f, 20.0f) )
  {
    overPlusX = true;
  }
  else
  {
    overPlusX = false;
  }
  
  if ( overCircle(widthPattern, posYOverview + width*0.1f, 20.0f) )
  {
    overMinusX = true;
  }
  else
  {
    overMinusX = false;
  }
}
  public void settings() {  size(300, 500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "StrickApp" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
