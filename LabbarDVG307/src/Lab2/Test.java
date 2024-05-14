package Lab2;

public class Test {
	public static void main(String [] args) {
		int[][] raster = new int[1100][1100];
		
		//Makes an array of algorithm objects		
		Algorithms [] algs = {new Algorithms("\\H:\\Algoritmer DVG307\\Lab2\\points_640000.txt", 20000),
	               new Algorithms("\\H:\\Algoritmer DVG307\\Lab2\\points_640000.txt", 40000),
	               new Algorithms("\\H:\\Algoritmer DVG307\\Lab2\\points_640000.txt", 80000),
	               new Algorithms("\\H:\\Algoritmer DVG307\\Lab2\\points_640000.txt", 160000),
	               new Algorithms("\\H:\\Algoritmer DVG307\\Lab2\\points_640000.txt", 320000),
	               new Algorithms("\\H:\\Algoritmer DVG307\\Lab2\\points_640000.txt", 640000)};
		
		try {
		    Thread.sleep(3000); // sleep for 2 seconds
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		int size = 20000;
		
		//iterates over each object
		for(Algorithms a : algs) {
			int start = 0;
			int next = 1;
			double best = 0;
			String minDist = "";
			//resets the time before each iteration
			double b1 = System.currentTimeMillis();
			minDist=a.getMinDist(start,next,best,size,minDist);
			double b2 = System.currentTimeMillis();
			double difB = b2-b1;
			//prints the average time for each test
			System.out.println("Time for execution is " + difB +" ms. Where the size of data is "+size+" and where "+minDist);
			System.out.println("---------------------------------------------");
			System.out.println();
			
			//Increases the size of lines by 2 times
			size = size*2;
		}		
	}
}
