package Lab4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BST<T extends Comparable<? super T>, E>{
	private BSTNode<T> root;
	private int counter = 0;
	
	public BST(){
		
	}
	private class BSTNode<NT extends Comparable<? super NT>>{
		private BSTNode<NT> l;
		private BSTNode<NT> r;
		private NT value;
		private int ID;
		
		private BSTNode(NT value){
			this.value = value;
		}
		
		///Adds a node to the tree
		private void insert(NT value){
			//-1 if this.value < value
			//1 if this.value > value
			//0 if this.value == value
			int dir = this.value.compareTo(value);
			if(dir == 0)System.err.println("Value already exist");
			else if(dir > 0){
				if(l==null){
					l = new BSTNode(value);
					l.ID = counter;
					counter++;
				}else l.insert(value);
			} else {
				if(r==null){
					r = new BSTNode(value);
					r.ID = counter;
					counter++;
				}else r.insert(value);
			}
		}

		//Fins a value in the tree 
		private int find(NT value){
			int dir = this.value.compareTo(value);
			int id = -1;
			if(dir == 0){
				id = this.ID;
			}
			else if(dir > 0){
				if(l!=null){
					id= l.find(value);
				}
			} else {
				if(r!=null){
					 id=r.find(value);
				}
			}
			return id;
			
			
		}
		//Prints the depth first
		private String printTreeDF(String str) {
			//Prints the left side values first than the right side
			if(l != null) {
				str = l.printTreeDF(str);
				str +=" {"+l.ID+":"+l.value+"}";
			}
			if(r != null) {
				str = r.printTreeDF(str);
				str +=" {"+r.ID+":"+r.value+"}";
			}
			return str;
		}
		//Prints the width first
		private String printTreeBF(Queue<BSTNode<T>> queue,String str) {
  
			if(queue.isEmpty()) {
				return str;	
			}else {
				//Removes the first value from the queue
				BSTNode<T> tempNode = queue.poll();
		        str += " {"+tempNode.ID+":"+tempNode.value+"}";
		        
		        //Adds the left and right value to the queue
				if(tempNode.l != null) {
					queue.add(tempNode.l);
				}
				if(tempNode.r != null) {
					queue.add(tempNode.r);
				}
				return printTreeBF(queue,str);
			}
	        
		}
		//Gets a string of the whole tree
		private String getString(BSTNode<T> node,String str,int space, int count){
			
			if(node == null) {
				return str;
			}else {
				
				/*The structure will be like this
				
				*							
				*						{4:30}
				*				{2:25}
				*						{6:24}
				*		{0:20}
				*						{5:18}
				*				{1:15}
				*						{3:10}
				*
				*
				*/
				space += count;
				//Goes threw the right side nodes first
				str=getString(node.r,str, space,count);
				 
				//Adds to the string
				str+="\n";
			    for (int i = count; i < space; i++)
			    	str+=" ";
			    str+="{"+node.ID+":"+node.value +"}"+ "\n";
			    //Goes threw the left side nodes
			    str=getString(node.l,str, space,count);
			}
			return str;
		}
		
	}
	
	public static int[] loadList(String path) throws FileNotFoundException,IOException {

		  ArrayList<Integer> list = new ArrayList<Integer>();
		 
		  BufferedReader in = new BufferedReader(new FileReader(path));
		  String l;
		  while((l = in.readLine()) != null) {		 
			  list.add(Integer.parseInt(l));
		  }
		  in.close();
		  int[] returnList = new int[list.size()];
		  int data;
		 
		  for(int i = 0; i < list.size(); i++) {
			  data = list.get(i);
			  returnList[i] = data;
		  }
		  return returnList;
	}
	

	public void insert(T value){
		if(root == null){
			root = new BSTNode(value);
			root.ID=counter;
			counter++;
		}else root.insert(value);
	}
	
	public int find(T value){
		int id = -1;
		if(root != null) id = root.find(value);
		return id;
	}
	
	public String printTreeDF(){
		return "DF: "+root.printTreeDF("")+" {"+root.ID+":"+root.value+"}";
	}
	
	
	public String printTreeBF(){
		Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
		queue.add(root);
		String str = root.printTreeBF(queue,"");
		return "BF: "+str;
	}
	@Override
	public String toString(){
		String str ="Whole tree: "+root.getString(root,"",5,10);
		return str;
	}
	

}

