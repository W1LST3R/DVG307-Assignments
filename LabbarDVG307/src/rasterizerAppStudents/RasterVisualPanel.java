package rasterizerAppStudents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RasterVisualPanel extends JPanel
  {
  private IRasterModel rasterModel;
  private IRasterizer rasterizer;
  private int selCnt = 0;
  private int [] selX = {-1, -1};
  private int [] selY = {-1, -1};
  private final double SCALE = 0.95;
  
  public RasterVisualPanel(IRasterizer rasterizer)
    {
    this(new RasterModel(64, 64), rasterizer);
    }
  
  public RasterVisualPanel(IRasterModel rasterModel, IRasterizer rasterizer)
    {
    super(true);
    this.setBackground(Color.WHITE);
    rasterizer.setModel(rasterModel);
    this.rasterModel = rasterModel;
    this.rasterizer = rasterizer;
    setFocusable(true);
    requestFocus();
    addListeners();
    }
  
  private void orderSelected()
    {
    if(selX[0] > selX[1]) // Swap if wrong order
      {
      selX[1] = selX[0] + selX[1];
      selX[0] = selX[1] - selX[0];
      selX[1] = selX[1] - selX[0];
      selY[1] = selY[0] + selY[1];
      selY[0] = selY[1] - selY[0];
      selY[1] = selY[1] - selY[0];
      }
    }
  
  private void clear()
    {
    rasterModel.clear();
    selCnt = 0;
    selX = new int [] {-1, -1};
    selY = new int [] {-1, -1};
    }
  
  public void addListeners()
    {
    this.addKeyListener(new KeyAdapter() {
    public void keyPressed(KeyEvent e)
      {
      switch(e.getKeyCode())
        {
        case KeyEvent.VK_ENTER:
        case KeyEvent.VK_SPACE:
        case KeyEvent.VK_C: // clear
          clear();
          break;
        case KeyEvent.VK_B: // Bresenham
          if (selCnt > 1)
            {
            orderSelected();
            rasterizer.rasterBresenham(selX[0], selY[0], selX[1], selY[1]);
            }
          break;
        case KeyEvent.VK_D: // DDA
          if (selCnt > 1)
            {
            orderSelected();
            rasterizer.rasterDDA(selX[0], selY[0], selX[1], selY[1]);
            }
          break;
        case KeyEvent.VK_F: // Dummy
          orderSelected();
          rasterizer.rasterRandom(selX[0], selY[0], selX[1], selY[1]);
          selCnt = 0;
          break;
        case KeyEvent.VK_UP: // Double size
          rasterModel.resize(rasterModel.width() * 2, rasterModel.height() * 2);
          selCnt = 0;
          break;
        case KeyEvent.VK_DOWN: // Half size
          if(rasterModel.width() >= 4 && rasterModel.height() >= 4)
            {
            rasterModel.resize((rasterModel.width() + 1) / 2, (rasterModel.height() + 1) / 2);
            selCnt = 0;
            }
          break;
        }
      }
    public void keyReleased(KeyEvent e)
      {
      repaint();
      }
    });
    MouseAdapter ma = new MouseAdapter()
      {
      private boolean pixelSelected = false;
      private void handle(MouseEvent e, boolean dragged)
        {
        int size = (int)(Math.min(getHeight(), getWidth()) * SCALE);
        double xStep = (double)size / rasterModel.width();
        double yStep = (double)size / rasterModel.height();
        int midX = getWidth() / 2;
        int midY = getHeight() / 2;
        int x = e.getX();
        int y = e.getY();
        int relX = (int)((x - (midX - size/2.0))/xStep);
        int relY = rasterModel.height() - 1 - (int)((y - (midY - size/2.0))/yStep);
        if(relX >= 0 && relX < rasterModel.width() && relY >= 0 && relY < rasterModel.height())
          {
          selX[selCnt % 2] = relX;
          selY[selCnt % 2] = relY;
          if(!dragged)
            pixelSelected = true;
          repaint();
          }    
        }
      
      @Override
      public void mousePressed(MouseEvent e)
        {
        handle(e, false);
        }
      
      @Override
      public void mouseReleased(MouseEvent e)
        {
        if(pixelSelected)
          selCnt++;
        pixelSelected = false;
        }
      
      @Override
      public void mouseDragged(MouseEvent e)
        {
        handle(e, true);
        }
      };
    this.addMouseListener(ma);
    this.addMouseMotionListener(ma);
    }
  
  @Override
  public void paintComponent(Graphics g)
    {
	super.paintComponent(g);
    int h = rasterModel.height();
    int w = rasterModel.width();
    int size = (int)(Math.min(getHeight(), getWidth()) * SCALE);
    double xStep = (double)size / w;
    double yStep = (double)size / h;
    int midX = getWidth() / 2;
    int midY = getHeight() / 2;
    var g2d = (Graphics2D) g;
    var bs1 = new BasicStroke(2);
    var bs3 = new BasicStroke(1);
    Color color = g2d.getColor();
    // Draw selected "pixels"
    g2d.setColor(Color.black);
    for(int i = 0; i < 2; i++)
      {
      if(selX[i] > -1 && selY[i] > -1)
        {
        int y = (int)(0.5 + midY - size / 2 + (h - 1 - selY[i]) * yStep);
        int x = (int)(0.5 + midX - size / 2 + selX[i] * xStep);
        g2d.fillRect(x, y, (int)(0.5 + xStep), (int)(0.5 + yStep));
        }
      }
    g2d.setColor(color);
    int y0 = midY - size / 2;
    int y1 = midY + size / 2;
    // Draw vertical lines
    for(int c = 0; c < w + 1; c++)
      {
      int x = (int)(0.5 + midX - size / 2 + c * xStep);
      if(c == 0 || c == w)
        {
        g2d.setStroke(bs1);
        g2d.drawLine(x, y0, x, y1);
        }
      else if(w <= 200)
        {
        g2d.setStroke(bs3);
        g2d.drawLine(x, y0, x, y1);
        }
      }
    int x0 = midX - size / 2;
    int x1 = midX + size / 2;
    // Draw horizontal lines
    for(int r = 0; r < h + 1; r++)
      {
      int y = (int)(0.5 + midY - size / 2 + r * yStep);
      if(r == 0 || r == h)
        {
        g2d.setStroke(bs1);
        g2d.drawLine(x0, y, x1, y);
        }
      else if(h <= 200)
        {
        g2d.setStroke(bs3);
        g2d.drawLine(x0, y, x1, y);
        }
      }
    // Draw "pixels"
    for(int r = 0; r < h; r++)
      {
      int y = (int)(midY - size / 2 + (h - r - 1) * yStep);
      for(int c = 0; c < w; c++)
        {
        int x = (int)(midX - size / 2 + c * xStep);
        Color acolor = rasterModel.get(c, r); 
        if(acolor != null)
          {
          g2d.setColor(acolor);          
          g2d.fillRect(x, y, (int)(xStep + 1), (int)(yStep + 1));
          }
        }
      }
    g2d.setColor(color);
    g2d.drawString(String.format("(%d,%d)", w, h),  
                   (int)(midX + size / 2 + 4),
                   (int)(midY - size / 2 + 10));
    g2d.drawString(String.format("(%d,%d)", 0, 0),
                   (int)(midX - size / 2 - size/16),
                   (int)(midY - size / 2 + h * yStep));
    }
  }
