package rasterizerAppStudents;

public class AlgortihmTest {
	public static void main(String [] args) {
		int[][] raster = new int[1100][1100];
		
		//Makes an array of algorithm objects
		Algorithms [] algs = {new Algorithms("H:\\Algoritmer DVG307\\Lab1\\lines_6400.txt", 100),
				               new Algorithms("H:\\Algoritmer DVG307\\Lab1\\lines_6400.txt", 200),
				               new Algorithms("H:\\Algoritmer DVG307\\Lab1\\lines_6400.txt", 400),
				               new Algorithms("H:\\Algoritmer DVG307\\Lab1\\lines_6400.txt", 800),
				               new Algorithms("H:\\Algoritmer DVG307\\Lab1\\lines_6400.txt", 1600),
				               new Algorithms("H:\\Algoritmer DVG307\\Lab1\\lines_6400.txt", 3200),
				               new Algorithms("H:\\Algoritmer DVG307\\Lab1\\lines_6400.txt", 6400)};
		
		int size = 100;
		
		//iterates over each object
		for(Algorithms a : algs) {
			
			//resets the time before each iteration
			double difB = 0;
			double difD = 0;
			
			//Iterates 100 times on each size
			for (int p = 0; p < 100;p++) {
				
				//Does bresenham for size number of times
				double b1 = System.currentTimeMillis();
				for(int i = 0; i<size;i++) {
					a.bresenham(raster, a.getX1(i), a.getY1(i), a.getX2(i), a.getY2(i));
				}
				
				double b2 = System.currentTimeMillis();
				difB += b2-b1;
				
				//Does dda for size number of times
				double d1 = System.currentTimeMillis();
				for(int i = 0; i<size;i++) {
					a.dda(raster, a.getX1(i), a.getY1(i), a.getX2(i), a.getY2(i));
				}
				double d2 = System.currentTimeMillis();
				difD += d2-d1;
			}
			//prints the average time for each test
			System.out.println("Time for bresenham execution " +size+" : " + difB/100 +" ms");
			System.out.println("Time for dda execution " +size+" : " + difD/100+" ms");
			System.out.println("---------------------------------------------");
			System.out.println();
			
			//Increases the size of lines by 2 times
			size = size*2;
		}		
	}
}
