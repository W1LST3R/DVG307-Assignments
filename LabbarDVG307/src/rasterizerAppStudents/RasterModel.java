package rasterizerAppStudents;

import java.awt.Color;

public class RasterModel implements IRasterModel
  {
  private Color [][] raster;
  
  public RasterModel(Color [][] raster)
    {
    this.raster = raster;
    }

  public RasterModel(int width, int height)
    {
    resize(width, height);
    }

  @Override
  public void resize(int width, int height)
    {
    this.raster = new Color[width][height];
    }
  
  @Override
  public void clear()
    {
    for(int x = 0; x < width(); x++)
      for(int y = 0; y < height(); y++)
        colorizePixel(x,  y,  null);
    }

  @Override
  public int width()
    {
    return raster.length;
    }

  @Override
  public int height()
    {
    return raster[0].length;
    }

  @Override
  public Color get(int x, int y)
    {
    Color result = null;
    try
      {
      result = raster[x][y];
      }
    catch(ArrayIndexOutOfBoundsException e)
      {
      }
    return result;
    }

  @Override
  public void colorizePixel(int x, int y, Color value)
    {
    try
      {
      raster[x][y] = value;
      }
    catch(ArrayIndexOutOfBoundsException e)
      {
      }
    }
  }