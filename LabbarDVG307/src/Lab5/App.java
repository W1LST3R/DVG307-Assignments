package Lab5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import Lab4.QuickSort;

public class App {
	

	//Adds and extracts values from a Heap, also gets the time for both functions
	private void testDummy() {
		int size = 16000;
		for(int j = 0; j<6;j++) {
			Integer[] array = new Integer[size];
			Heapish<Integer> heap = new MinHeap<Integer>(array);
			double t1 = System.currentTimeMillis();
			for (int i = 0; i < size; i++) {
				heap.insert((int) (100 * Math.random() + 1));
			}
			double t2 = System.currentTimeMillis();
			System.out.println("För insert "+size+" values: " + (t2 - t1) + " ms");
			//heap.print();
			t1 = System.currentTimeMillis();
			while(!heap.empty()) {
				heap.extract();
			}
			t2 = System.currentTimeMillis();
			System.out.println("För extract "+size+" values: " + (t2 - t1) + " ms");
			System.out.println(heap.isHeap());	
			size*=2;
		}
	}

	//Transforms an array to an list
	private static <T extends Comparable<T>> ArrayList<T> transformToList(T[] arr) {

		ArrayList<T> list = new ArrayList<T>();
		for (int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
		}
		return list;
	}
	//Transforms an list to an array
	private static <T extends Comparable<T>> T[] transformToArr(ArrayList<T> list) {
		T[] arr = (T[]) new Comparable[list.size()];
		for (int i = 0; i < list.size(); i++) {
			arr[i] = list.get(i);
		}
		return arr;
	}
	//Transforms an list to an Integer array
	private static <T extends Comparable<T>> Integer[] transformToArrInteger(ArrayList<T> list) {
		Integer[] arr = new Integer[list.size()];
		for (int i = 0; i < list.size(); i++) {
			arr[i] = (Integer) list.get(i);
		}
		return arr;
	}

	//Heap sort for an list
	private static <T extends Comparable<T>> ArrayList<T> heapSort(ArrayList<T> list, String choise) {
		//Makes a empty array of the same size as the list
		T[] temp = (T[]) new Comparable[list.size()];
		Heapish<T> heap;
		//decides if it should be a min or max heap
		if (choise == "MIN") {
			heap = new MinHeap<T>(temp);
		} else {
			heap = new MaxHeap<T>(temp);
		}
		//inserts and extracts all values
		//MinHeap will be sorted from high to low [9 8 6 3 2 1]
		//MaxHeap will be sorted from low to high [1 2 3 6 8 9]
		for (int i = 0; i < list.size(); i++) {
			heap.insert(list.get(i));
		}
		while(!heap.empty()) {
			T value = heap.extract();
			list.set(heap.size(), value);
		}
		return list;
	}

	//Heap sort for an array
	private static <T extends Comparable<T>> T[] heapSort(T[] arr, String choise) {
		//Makes a empty array of the same size as the arr
		T[] temp = (T[]) new Comparable[arr.length];
		Heapish<T> heap;
		//decides if it should be a min or max heap
		if (choise == "MIN") {
			heap = new MinHeap<T>(temp);
		} else {
			heap = new MaxHeap<T>(temp);
		}
		//inserts and extracts all values
		//MinHeap will be sorted from high to low [9 8 6 3 2 1]
		//MaxHeap will be sorted from low to high [1 2 3 6 8 9]
		for (int i = 0; i < arr.length; i++) {
			heap.insert(arr[i]);
		}
		while(!heap.empty()) {
			T value = heap.extract();
			arr[heap.size()] = value;
		}
		return arr;
	}

	public static void main(String[] args) {
		new App().testDummy();
		int size = 16000;
		for(int j = 0;j<6;j++ ) {
			Integer[] arrHeap = new Integer[size];
			Integer[] arrQuick = new Integer[size];
			ArrayList<Integer> listHeap = new ArrayList<Integer>();
			ArrayList<Integer> listQuick = new ArrayList<Integer>();
			for (int i = 0; i < arrHeap.length; i++) {
				int value =  (int) (100 * Math.random() + 1);
				arrHeap[i]=value;
				arrQuick[i]=value;
				listHeap.add(value);
				listQuick.add(value);
			}
			QuickSort qs = new QuickSort(arrQuick);
			QuickSort qsl = new QuickSort(transformToArr(listQuick));
			
			
			double q1 = System.currentTimeMillis();
			qs.sort(arrQuick, 0, arrQuick.length-1);
			double q2 = System.currentTimeMillis();
			
			// Type MIN to get a minHeap. Type MAX to get a maxHeap
			double t1 = System.currentTimeMillis();
			heapSort(arrHeap,"MIN");
			double t2 = System.currentTimeMillis();
			
			double ql1 = System.currentTimeMillis();
			Integer[] arrList = transformToArrInteger(listQuick);
			qs.sort(arrList, 0, arrList.length-1);
			transformToList(arrList);
			double ql2 = System.currentTimeMillis();
			
			// Type MIN to get a minHeap. Type MAX to get a maxHeap
			double tl1 = System.currentTimeMillis();
			heapSort(listHeap,"MIN");
			double tl2 = System.currentTimeMillis();
			
			System.out.println("HeapSort with array took "+(t2-t1)+" ms, with "+size+" values");
			System.out.println("QuickSort with array took "+(q2-q1)+" ms, with "+size+" values");
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("HeapSort with list took "+(tl2-tl1)+" ms, with "+size+" values");
			System.out.println("QuickSort with list took "+(ql2-ql1)+" ms, with "+size+" values");
			System.out.println("--------------------------------------------------------------------------");
			System.out.println();
			size=size*2;
		}
		
		Integer[] minArr = new Integer[30];
		Integer[] maxArr = new Integer[30];
		for (int i = 0; i < minArr.length; i++) {
			int value = (int) (30 * Math.random() + 1);
			minArr[i] =  value;
			maxArr[i]= value;
		}
		heapSort(minArr,"MIN");
		heapSort(maxArr,"MAX");
		String minStr = "[";
		String maxStr = "[";
		for(int i =0;i<minArr.length;i++) {
			minStr += " "+minArr[i];
			maxStr += " "+maxArr[i];
		}
		minStr += " ]";
		maxStr += " ]";
		System.out.println("MinHeap sorted: "+minStr);
		System.out.println("MaxHeap sorted: "+maxStr);
		System.out.println();
		System.out.println();
		System.out.println();

		
		//Two priorityQ one with Characters as weights and the other with Integers as weights
		PriorityQ_ToFill<String, Character> pq = new PriorityQ_ToFill<String, Character>(100);
		pq.insert("Jonas", 'a');
		pq.insert("Apan", 'e');
		pq.insert("Ara", 'd');
		pq.insert("Bengtsson", 'b');
		pq.insert("Sara", 't');
		pq.insert("Sverker", 's');
		pq.insert("Bo", 'k');
		pq.insert("Gunnarsson", 'l');
		pq.insert("12345", 'p');
		pq.insert("1", 'a');
		pq.insert("1234567", 'g');
		while(pq.size() > 0)
		  System.out.println(pq.extract());
		
		PriorityQ_ToFill<String, Integer> pqT = new PriorityQ_ToFill<String, Integer>(100);
		pqT.insert("Jonas", 5);
		pqT.insert("Apan", 4);
		pqT.insert("Ara", 3);
		pqT.insert("Bengtsson", 9);
		pqT.insert("Sara", 4);
		pqT.insert("Sverker", 7);
		pqT.insert("Bo", 2);
		pqT.insert("Gunnarsson", 10);
		pqT.insert("12345", 5);
		pqT.insert("1", 1);
		pqT.insert("1234567", 7);
		while(pqT.size() > 0)
		  System.out.println(pqT.extract());
	}

}
