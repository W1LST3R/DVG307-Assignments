package rasterizerAppStudents;

public interface IRasterizer
  {
  public void setModel(IRasterModel model);
  public void rasterDDA(int x0, int y0, int x1, int y1);
  public void rasterBresenham(int x0, int y0, int x1, int y1);
  public void rasterRandom(int x0, int y0, int x1, int y1);
  }
