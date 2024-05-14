package Lab4;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QuickSort qs = new QuickSort("H:\\Algoritmer DVG307\\Lab4\\gener.txt");
		
		int[] data = qs.getList();

		double qs1 = System.currentTimeMillis();
		qs.sort(data,0,data.length-1);
		double qs2 = System.currentTimeMillis();
		System.out.println("result for Upg 1 sort(): " +(qs2-qs1)+" ms" );
		qs1 = System.nanoTime();
		System.out.println(qs.find(3162819, data, 0, data.length-1));
		qs2 = System.nanoTime();
		System.out.println("result for Upg 1 find(): " +(qs2-qs1)+" ns" );
		System.out.println("---------------------------------------------" );

		BST bst = new BST<>();
		try {
			int[] dataBST = bst.loadList("H:\\Algoritmer DVG307\\Lab4\\gener.txt");
			
			//dataBST = new int[]{5, 6, 3, 1, 11 ,2,4,13,14,8,12};
			double bst1 = System.currentTimeMillis();
			for(int i = 0;i<dataBST.length;i++){
				bst.insert(dataBST[i]);
			}
			double bst2 = System.currentTimeMillis();
			System.out.println("result for Upg 2 insert(): " +(bst2-bst1)+" ms" );
			
			
			bst1 = System.nanoTime();
			System.out.println(bst.find(3162819));
			bst2 = System.nanoTime();
			System.out.println("result for Upg 2 find(): " +(bst2-bst1)+" ns" );
			//System.out.println(bst.printTreeDF());
			//System.out.println(bst.printTreeBF());
			//System.out.println(bst);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
