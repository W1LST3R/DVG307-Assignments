package rasterizerAppStudents;

import java.awt.Color;

public interface IRasterModel
  {
  public int width();
  public int height();
  public void colorizePixel(int x, int y, Color value);
  public Color get(int x, int y);
  public void clear();
  public void resize(int width, int height);
  }
