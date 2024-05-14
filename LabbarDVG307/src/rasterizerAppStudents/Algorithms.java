package rasterizerAppStudents;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Algorithms {

	private int[][] values; 
	public Algorithms(String path, int size){
		//Loads the 2 dimensional array with values
		try {
			values = loadList(path, size);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Gets the value from a specific row
	public int getX1(int row) {
		return values[row][0];
	}
	public int getX2(int row) {
		return values[row][2];
	}
	public int getY1(int row) {
		return values[row][1];
	}
	public int getY2(int row) {
		return values[row][3];
	}
	
	private static int[][] loadList(String path, int size) throws FileNotFoundException,IOException {
		  int s = 0;
		  ArrayList<Integer[]> list = new ArrayList<Integer[]>();
		 
		  BufferedReader in = new BufferedReader(new FileReader(path));
		  String l;
		  String[] elementBuffer;
		  Integer[] valueBuffer;
		  while((l = in.readLine()) != null && s < size) {
			  s++;
			  elementBuffer = l.trim().split(" ");
			  valueBuffer = new Integer[4];
			  valueBuffer[0] = Integer.parseInt(elementBuffer[0]);
			  valueBuffer[1] = Integer.parseInt(elementBuffer[1]);
			  valueBuffer[2] = Integer.parseInt(elementBuffer[2]);
			  valueBuffer[3] = Integer.parseInt(elementBuffer[3]);
			 
			  list.add(valueBuffer);
		  }
		  in.close();
		 
		  int[][] returnList = new int[list.size()][4];
		  Integer[] data;
		 
		  for(int i = 0; i < list.size(); i++) {
			  data = list.get(i);
			  returnList[i][0] = data[0];
			  returnList[i][1] = data[1];
			  returnList[i][2] = data[2];
			  returnList[i][3] = data[3];
		  }
		  return returnList;
	}

	//The algorithm for bresenham 
	public void bresenham(int[][] raster, int v0_x,int v0_y,int v1_x,int v1_y){
		int x = (int)v0_x; int y = (int)v0_y;
		int dx = (int) (v1_x - v0_x); int dy = (int) (v1_y - v0_y);
		int d = 2 * dy - dx;
		for (int i = 0; i < dx; i++) {
			colorizePixel (raster, x, y);
			if (d < 0) {
				d += (2 * dy);
			}else {
				d += (2 * (dy - dx)); 
				y++;
			}
			x++;
		}
	}
	
	//The algorithm for dda
	public void dda(int[][] raster, int x0, int y0, int x1, int y1) {
		int x = x0;
		double y = y0;
		double a = ((double)(y1 - y0))/(x1 - x0);
		while (x <= x1) {
			colorizePixel (raster, x, (int)Math.round (y));
			x++;
			y += a;
		}
	}
	
	public static void colorizePixel (int[][] raster, int x, int y) {
		raster[y][x] = 1;
	}
		
}
