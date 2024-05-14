package Lab2;

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
	public int[][] getValues(){
		return values;
	}
	//Gets the value from a specific row
	public int getX(int row) {
		return values[row][0];
	}

	public int getY(int row) {
		return values[row][1];
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
			  valueBuffer = new Integer[2];
			  valueBuffer[0] = Integer.parseInt(elementBuffer[0]);
			  valueBuffer[1] = Integer.parseInt(elementBuffer[1]);
			 
			  list.add(valueBuffer);
		  }
		  in.close();
		  
		  // returnList is 2D array, with size equal to amount of rows to be read from the file.
		  // Each row has structure [x1,y1,x2,y2].
		  int[][] returnList = new int[list.size()][2];
		  Integer[] data;
		 
		  for(int i = 0; i < list.size(); i++) {
			  data = list.get(i);
			  returnList[i][0] = data[0];
			  returnList[i][1] = data[1];
		  }
		  return returnList;
	}
	
	public void rekSearch(int start,int next,double best,int size,String minDist){
		double newBest = best;
		String newMin = minDist;
		if(start+1 == size-1 ) {
			 //result = minDist;
		}else {
			if(next == size){
				start++;
				next = start+1;
			}


			if(distance(getX(start), getX(next), getY(start), getY(next))<newBest || newBest == 0){
				newBest = distance(getX(start), getX(next), getY(start), getY(next));
				newMin = "distance between pointPair "+start+" and "+next+" has the shortest distance and it is "+newBest;
			}
			
			rekSearch(start, next+1, newBest, size,newMin);
		}
	}
	
	public String getMinDist(int start,int next,double best,int size,String minDist){
		double newBest = best;
		String newMin = minDist;
		while(!(start+1 == size-1 )) {
			if(next == size){
				start++;
				next = start+1;
			}
			if(distance(getX(start), getX(next), getY(start), getY(next))<newBest || newBest == 0){
				newBest = distance(getX(start), getX(next), getY(start), getY(next));
				newMin = "distance between pointPair "+start+" and "+next+" has the shortest distance and it is "+newBest;
			}
			next++;
		}
		return newMin;
	}

	public double distance(int x0,int x1, int y0,int y1){
		return Math.sqrt(Math.pow(x1-x0,2)+Math.pow(y1-y0,2));
	}
		
}
