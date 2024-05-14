package rasterizerAppStudents;

import java.awt.Color;
import java.util.Random;

public class StudentRasterizer implements IRasterizer
  {
  private IRasterModel model;
  
  public StudentRasterizer(IRasterModel model)
    {
    setModel(model);
    }

  public StudentRasterizer()
    {
    }

  @Override
  public void setModel(IRasterModel model)
    {
    this.model = model;
    }

  @Override
  public void rasterDDA(int x0, int y0, int x1, int y1)
    {
    // Students: Add your DDA code here
    // set a pixel in the model by:
    // model.colorizePixel(x, y, Color.blue) // choose different colors for different impls
    // ...
    model.colorizePixel(x0,  y0,  Color.blue); // And remove this
    model.colorizePixel(x1,  y1,  Color.blue); // And remove this
    }

  @Override
  public void rasterBresenham(int x0, int y0, int x1, int y1)
    {
    // Students: Add your Bresenham code here
    // set a pixel in the model by:
    // model.colorizePixel(x, y, Color.green) // choose different colors for different impls
    // ...
    model.colorizePixel(x0,  y0,  Color.red); // And remove this
    model.colorizePixel(x1,  y1,  Color.red); // And remove this
    }

  @Override
  public void rasterRandom(int x0, int y0, int x1, int y1)
    {
    int x = x0;
    int y = y0;
    Random random = new Random();
    model.colorizePixel(x0, y0, Color.black);
    while(x != x1 || y != y1) // takes any step that is closer to (x1, y1)
      {
      int dx = random.nextInt() % 2;
      int dy = random.nextInt() % 2;
      if(dx * (2 * (x - x1) + dx) + dy * (2 * (y - y1) + dy) < 0)
        {
        x += dx;
        y += dy;
        model.colorizePixel(x, y, Color.darkGray);
        }
      }
    model.colorizePixel(x1, y1, Color.black);
    }
  }
