package rasterizerAppStudents;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class RasterVisualApp extends JFrame
  {
  private RasterVisualPanel rasterVisualPanel; 
  public RasterVisualApp()
    {
    super("Rasterization algorithms visualization");
    IRasterizer rasterizer = new StudentRasterizer();
    rasterVisualPanel = new RasterVisualPanel(rasterizer);
    Font font = new Font(Font.MONOSPACED, Font.BOLD, 16);
    rasterVisualPanel.setFont(font);
    add(rasterVisualPanel);
    makeMenues();
    setBackground(Color.WHITE);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(1280, 1024);
    setLocationRelativeTo(null);
    setVisible(true);
    }
  
  public void makeMenues()
    {
    JMenuBar menuBar = new JMenuBar();
    JMenu help = new JMenu("Help");
    JMenuItem mouseMI = new JMenuItem("Mouse commands");
    mouseMI.addActionListener((e) -> showMouseHelp());
    JMenuItem keybMI = new JMenuItem("Keyboard commands");
    keybMI.addActionListener((e) -> showKeyboardHelp());
    JMenuItem aboutMI = new JMenuItem("About this tool");
    aboutMI.addActionListener((e) -> showAbout());
    help.add(mouseMI);
    help.add(keybMI);
    help.add(aboutMI);
    menuBar.add(help);
    this.setJMenuBar(menuBar);
    }
  
  public void showKeyboardHelp()
    {
    String html = "<html><table>"
                + "<tr><th align=\"left\">Key</th><th align=\"left\">Action</th></tr>"
                + "<tr><td><b>&#8593</b></td><td>Double size</td></tr>"
                + "<tr><td><b>&#8595</b></td><td>Half size</td></tr>"
                + "<tr><td><b>B</b></td><td>Use Besenham's</td></tr>"
                + "<tr><td><b>D</b></td><td>Use DDA</td></tr>"
                + "<tr><td><b>F</b></td><td>Use dummy</td></tr>"
                + "<tr><td><b>ENTER</b></td><td>Clear</td></tr>"
                + "<tr><td><b>SPACE</b></td><td>Clear</td></tr>"
                + "<tr><td><b>C</b></td><td>Clear</td></tr>"
                + "</table>";
    JOptionPane.showMessageDialog(this, html, "Keyboard Commands", JOptionPane.PLAIN_MESSAGE);
    }

  public void showMouseHelp()
    {
    String html = "<html><p>"
                + "Click on two points and then use keyboard commands"
                + "</html>";
    JOptionPane.showMessageDialog(this, html, "Mouse Commands", JOptionPane.PLAIN_MESSAGE);
    }

  public void showAbout()
    {
    String html = "<html><table>"
                + "<tr><th align=\"center\">Rasterization algorithms visualization</th></tr>"
                + "<tr><td></td></tr>"
                + "<tr><td align=\"center\">Version 1.0</td></tr>"
                + "<tr><td align=\"center\">2024-04-03</td></tr>"
                + "<tr><td align=\"center\">Copyright &#169 2024 - 2030 Jonas Boustedt</td></tr>"
                + "<tr><td align=\"center\">Alla rättigheter förbehålls</td></tr>"
                + "</table>";
    JOptionPane.showMessageDialog(this, html, "About rasterization algorithms visualization", JOptionPane.PLAIN_MESSAGE); 
    }
  
  public static void main(String [] args)
    {
    SwingUtilities.invokeLater(new Runnable() {
    public void run()
      {
      new RasterVisualApp();
      }
    });
    }
  }
