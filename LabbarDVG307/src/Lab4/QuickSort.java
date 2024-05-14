package Lab4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class QuickSort <T extends Comparable<T>> {
	
	private int[] list;
	private T[] tList;
	public QuickSort(String path,int size) {
		try {
			list = loadList(path,size);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public QuickSort(String path) {
		try {
			list = loadList(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public QuickSort(int[] myList) {
		list = myList;
	}
	public QuickSort(T[] myList) {
		tList = myList;
	}
	
	public T[] getTList() {
		return tList;
	}
	
	public int[] getList() {
		return list;
	}
	private static int[] loadList(String path,int size) throws FileNotFoundException,IOException {
		int s = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		 
		  BufferedReader in = new BufferedReader(new FileReader(path));
		  String l;
		  while((l = in.readLine()) != null && size > s) {
			  s++;
			  list.add(Integer.parseInt(l));
		  }
		  in.close();
		  
		  // returnList is 2D array, with size equal to amount of rows to be read from the file.
		  // Each row has structure [x1,y1,x2,y2].
		  int[] returnList = new int[size];
		  int data;
		 
		  for(int i = 0; i < list.size(); i++) {
			  data = list.get(i);
			  returnList[i] = data;
		  }
		  return returnList;
	}
	
	private static int[] loadList(String path) throws FileNotFoundException,IOException {

		  ArrayList<Integer> list = new ArrayList<Integer>();
		 
		  BufferedReader in = new BufferedReader(new FileReader(path));
		  String l;
		  while((l = in.readLine()) != null) {		 
			  list.add(Integer.parseInt(l));
		  }
		  in.close();
		  
		  // returnList is 2D array, with size equal to amount of rows to be read from the file.
		  // Each row has structure [x1,y1,x2,y2].
		  int[] returnList = new int[list.size()];
		  int data;
		 
		  for(int i = 0; i < list.size(); i++) {
			  data = list.get(i);
			  returnList[i] = data;
		  }
		  return returnList;
	}

	//Sorts the values, smaller values to the left of the pivot and bigger values to the right
	public void sort(int[] data,int left,int right) {
		int i = left;
		int j = right;
		int pivot = data[i];
		do{
			//If the value at i is bigger than the pivot i gets moved to the right
			while(data[i]<pivot) i++;
			//If the value at j is smaller than the pivot i gets moved to the left
			while(pivot < data[j])j--;
			//the value switches places
			if (i<=j){
				swap(data,i,j);
				i++;
				j--;
			}
		}while(i<=j);
		if(left<j)sort(data, left, j);
		if(i<right)sort(data,i,right);
	}
	
	public void printArr(int[] arr) {
		for(int i = 0; i<arr.length;i++) {
			System.out.println(arr[i]);
		}
	}
	
	public void sort(Integer[] data,int left,int right) {
		int i = left;
		int j = right;
		int pivot = data[i];
		do{
			//If the value at i is bigger than the pivot i gets moved to the right
			while(data[i]<pivot) i++;
			//If the value at j is smaller than the pivot i gets moved to the left
			while(pivot < data[j])j--;
			//the value switches places
			if (i<=j){
				swap(data,i,j);
				i++;
				j--;
			}
		}while(i<=j);
		if(left<j)sort(data, left, j);
		if(i<right)sort(data,i,right);
	}
	
	public void printArr(Integer[] arr) {
		for(int i = 0; i<arr.length;i++) {
			System.out.println(arr[i]);
		}
	}
	
	//Changes position of two values in an array
	public void swap(int[] arr,int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public void swap(Integer[] arr,int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	//Searches for a value in a sorted array
	public boolean find(int value,int[] data,int left,int right) {
		//Gets the index of the value in the middle
		int middle = (left+right)/2;
		if(value == data[middle]){
			return true;
		}else {
			//if the value is smaller than the middle value it will search on the left side of the middle value
			if(value<data[middle]) {
				if(data[left]==data[right]) {
					return false;
				}else return find(value, data, left, middle-1);
			}
			//if the value is bigger than the middle value it will search on the right side of the middle value
			else if(value>data[middle]) {
				if(data[left]==data[right]) {
					return false;
				}else return find(value,data, middle+1, right);
			}
			return false;
		}
		
	}
	
	public boolean find(int value,Integer[] data,int left,int right) {
		//Gets the index of the value in the middle
		int middle = (left+right)/2;
		if(value == data[middle]){
			return true;
		}else {
			//if the value is smaller than the middle value it will search on the left side of the middle value
			if(value<data[middle]) {
				if(data[left]==data[right]) {
					return false;
				}else return find(value, data, left, middle-1);
			}
			//if the value is bigger than the middle value it will search on the right side of the middle value
			else if(value>data[middle]) {
				if(data[left]==data[right]) {
					return false;
				}else return find(value,data, middle+1, right);
			}
			return false;
		}
		
	}
}
