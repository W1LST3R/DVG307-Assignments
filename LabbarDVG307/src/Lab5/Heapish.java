package Lab5;

public interface Heapish<T> {
	public void insert(T data);
	  public T extract();
	  public T top();
	  public void clear();
	  public int size();
	  public boolean empty();
	  public boolean isHeap();
	  public void print();
}
