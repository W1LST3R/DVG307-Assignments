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
	private Integer[] array = new Integer[1000];
	private Heapish<Integer> heap = new MinHeap<Integer>(array);

	private void testDummy() {
		double t1 = System.nanoTime();
		for (int i = 0; i < 20; i++) {
			heap.insert((int) (100 * Math.random() + 1));
		}
		//hej
		double t2 = System.nanoTime();
		System.out.println("För insert: " + (t2 - t1) + " ns");
		heap.print();
		t1 = System.nanoTime();
		for (int i = 0; i < 20; i++) {
			heap.extract();
		}
		t2 = System.nanoTime();
		System.out.println("För extract: " + (t2 - t1) + " ns");
		System.out.println(heap.isHeap());

		/*
		 * while (!heap.empty()) System.out.println(heap.extract());
		 */
	}

	private static <T extends Comparable<T>> ArrayList<T> transformToList(T[] arr) {

		ArrayList<T> list = new ArrayList<T>();
		for (int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
		}
		return list;
	}
	
	private static <T extends Comparable<T>> T[] transformToArr(ArrayList<T> list) {
		T[] arr = (T[]) new Comparable[list.size()];
		for (int i = 0; i < list.size(); i++) {
			arr[i] = list.get(i);
		}
		return arr;
	}
	
	private static <T extends Comparable<T>> Integer[] transformToArrInteger(ArrayList<T> list) {
		Integer[] arr = new Integer[list.size()];
		for (int i = 0; i < list.size(); i++) {
			arr[i] = (Integer) list.get(i);
		}
		return arr;
	}

	private static <T extends Comparable<T>> ArrayList<T> heapSort(ArrayList<T> list, String choise) {
		T[] temp = (T[]) new Comparable[list.size()];
		Heapish<T> heap;
		if (choise == "MIN") {
			heap = new MinHeap<T>(temp);
		} else {
			heap = new MaxHeap<T>(temp);
		}

		for (int i = 0; i < list.size(); i++) {
			heap.insert(list.get(i));
		}
		while(!heap.empty()) {
			T value = heap.extract();
			list.set(heap.size(), value);
		}
		return list;
	}

	private static <T extends Comparable<T>> T[] heapSort(T[] arr, String choise) {
		T[] temp = (T[]) new Comparable[arr.length];
		Heapish<T> heap;
		if (choise == "MIN") {
			heap = new MinHeap<T>(temp);
		} else {
			heap = new MaxHeap<T>(temp);
		}
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
		//new App().testDummy();
		int size = 16000;
		for(int j = 0;j<6;j++ ) {
			Integer[] arr = new Integer[size];
			for (int i = 0; i < arr.length; i++) {
				arr[i] =  (int) (1000 * Math.random() + 1);
			}
			Integer[] arrCpy = arr; 
			ArrayList<Integer> list = transformToList(arr);
			ArrayList<Integer> listCpy = list;
			
			QuickSort qs = new QuickSort(arrCpy);
			QuickSort qsl = new QuickSort(transformToArr(listCpy));
			
			
			double q1 = System.currentTimeMillis();
			qs.sort(arrCpy, 0, arrCpy.length-1);
			double q2 = System.currentTimeMillis();
			
			// Type MIN to get a minHeap. Type MAX to get a maxHeap
			double t1 = System.currentTimeMillis();
			heapSort(arr,"MIN");
			double t2 = System.currentTimeMillis();
			
			double ql1 = System.currentTimeMillis();
			Integer[] arrList = transformToArrInteger(listCpy);
			qs.sort(arrList, 0, arrList.length-1);
			transformToList(arrList);
			double ql2 = System.currentTimeMillis();
			
			// Type MIN to get a minHeap. Type MAX to get a maxHeap
			double tl1 = System.currentTimeMillis();
			heapSort(list,"MIN");
			double tl2 = System.currentTimeMillis();
			
			
			/*String str = "[";
			String strQS = "[";
			for(int i =0;i<arr.length;i++) {
				str += " "+arr[i];
				strQS += " "+arrCpy[i];
			}
			str += " ]";
			strQS += " ]";*/
			
			//System.out.println(str);
			//System.out.println(strQS);
			System.out.println("HeapSort with array took "+(t2-t1)+" ms, with "+size+" values");
			System.out.println("QuickSort with array took "+(q2-q1)+" ms, with "+size+" values");
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("HeapSort with list took "+(tl2-tl1)+" ms, with "+size+" values");
			System.out.println("QuickSort with list took "+(ql2-ql1)+" ms, with "+size+" values");
			System.out.println("--------------------------------------------------------------------------");
			System.out.println();
			size=size*2;
		}
		
		System.out.println();
		System.out.println();
		//swé
		System.out.println();

		
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
	}

}
