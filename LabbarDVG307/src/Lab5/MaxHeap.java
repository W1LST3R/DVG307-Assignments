package Lab5;

public class MaxHeap <T extends Comparable<T>> implements Heapish<T> {
	private T[] storage;
	private int N = 0;

	public MaxHeap(T[] storage) // user injects array big enough
	{
		this.storage = storage;
	}

	public void insert(T data) // add and restore heap props (HP)
	{
		if (size() == 0) {
			storage[N] = data;
			N++;
		} else {
			storage[N] = data;
			int child = N;
			N++;
			int parent = (child - 1) / 2;
			while (storage[parent].compareTo(data) < 0) {
				swap(parent, child);
				child = parent;
				parent = (child - 1) / 2;
			}
		}

	}

	public T extract() // remove smallest, restore HP and return
	{
		T top = top();
		storage[0] = storage[N - 1];
		storage[N - 1] = null;
		N--;
		int parent = 0;
		int leftChild;
		int rightChild;
		int lastIndex = N-1;
		String side ="Right";
		while (lastIndex > parent) {
			leftChild = (2 * parent) + 1;
			rightChild = (2 * parent) + 2;
			if(rightChild < lastIndex && leftChild < lastIndex){
				if(storage[parent].compareTo(storage[rightChild]) < 0 || storage[parent].compareTo(storage[leftChild]) < 0) {
					if (storage[rightChild].compareTo(storage[leftChild]) == 0) {
						if (side == "Right") {
							swap(parent, rightChild);
							parent = rightChild;
							side ="Left";
						} else {
							swap(parent, leftChild);
							parent = leftChild;
							side ="Right";
						}
					} else if (storage[rightChild].compareTo(storage[leftChild]) < 0) {
						swap(parent, leftChild);
						parent = leftChild;

					} else {
						swap(parent, rightChild);
						parent = rightChild;

					}
				}else parent = N;
			}else if(leftChild < lastIndex) {
				if(storage[parent].compareTo(storage[leftChild]) < 0) {
						swap(parent, leftChild);
						parent = leftChild;
				}else parent = N;
			}else parent = N;
		}

		return top;
	}

	public T top() // return smallest, don't remove
	{
		return storage[0];
	}

	public void clear() // make heap appear empty
	{
		N = 0;
	}

	public int size() // well, you know
	{
		return N;
	}

	public boolean empty() // well, you know
	{
		boolean flag = true;
		if (size() != 0)
			flag = false;
		return flag;
	}

	public boolean isHeap() // bjussar på denna då den kan hjälpa debugging
	{
		for (int i = 1; i < N; i++)
			if (storage[i].compareTo(storage[(i - 1) / 2]) < 0)
				return false;
		return true;
	}

	public void swap(int i, int j) {
		T temp = storage[i];
		storage[i] = storage[j];
		storage[j] = temp;
	}

	public void print() { // se nedan
		if (N > 0) // fulmetod för att se heapen. ok om N < 80 typ
		{
			System.out.format("N = %d\n", N);
			double log2 = Math.log(N) / Math.log(2);
			int depth = (int) (1 + log2);
			int fullN = (int) (Math.pow(2, depth)) - 1;
			int div = 1;
			for (int e = 0; e < (int) (1 + log2); e++) {
				for (int i = 0; i < Math.pow(2, e); i++) {
					int idx = (int) (Math.pow(2, e) - 1 + i);
					if (idx < N) {
						if (i == 0)
							System.out.format("%s%02d", strn((fullN - 1) / div - 1), storage[idx]);
						else
							System.out.format("%s%02d", strn(2 * (fullN - 1) / div - 1), storage[idx]);
					}
				}
				div *= 2;
				System.out.println();
			}
		}
		System.out.println("\n- - - - - - - - - - - - - - - - - - -");
	}

	private String strn(int n) {
		String result = "";
		while (n-- > 0)
			result += " ";
		return result;
	}
}
