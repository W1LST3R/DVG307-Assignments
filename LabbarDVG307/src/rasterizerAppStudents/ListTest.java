package rasterizerAppStudents;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class ListTest {
	public static void main(String[] args) {
		
		Random random = new Random(10);
		
		ArrayList<Integer> arrList = new ArrayList<>();
		LinkedList<Integer> lnkList = new LinkedList<>();

		int size = 10000;
		for(int p = 0; p<4;p++) {
			
			//resets the time before each iteration
			double aDiff = 0;
			double lDiff = 0;
			double amDiff = 0;
			double lmDiff = 0;
			//Iterates 100 times on each size
			for(int j = 0; j<100;j++) {
				
				//Adds the elements to the Arraylist and then adds a value on the first position and the middle position
				for(int i = 0; i<size;i++) {
					arrList.add(random.nextInt());
				}
				double a1 = System.nanoTime();
				arrList.add(0,random.nextInt());
				double a2 = System.nanoTime();
				aDiff += a2-a1;
				
				double am1 = System.nanoTime();
				arrList.add(((int)(arrList.size()-1)/2)-1,random.nextInt());
				double am2 = System.nanoTime();
				amDiff += am2-am1;
				
				//Adds the elements to the Linkedlist and then adds a value on the first position and the middle position
				for(int i = 0; i<size;i++) {
					lnkList.add(random.nextInt());
				}
				double l1 = System.nanoTime();
				lnkList.add(0, random.nextInt());
				double l2 = System.nanoTime();
				lDiff += l2-l1;	

				double lm1 = System.nanoTime();
				arrList.add(((int)(lnkList.size()-1)/2)-1,random.nextInt());
				double lm2 = System.nanoTime();
				lmDiff += lm2-lm1;
				
				//Clears the lists for the next iteration
				arrList.clear();
				lnkList.clear();
			}
			
			//prints the average time for each test
			System.out.println("Time for arraylist execution " +size+" for first value : " + aDiff/100+" ns");
			System.out.println("Time for linkedlist execution " +size+" for first value : " + lDiff/100+" ns");
			System.out.println("---------------------------------------------");
			System.out.println("Time for arraylist execution " +size+" for middle value : " + amDiff/100+" ns");
			System.out.println("Time for linkedlist execution " +size+" for middle value : " + lmDiff/100+" ns");
			System.out.println("---------------------------------------------");
			System.out.println();
			
			//Increases the size of elements by 4 times
			size=size*4;
		}
		
		
	}
}
