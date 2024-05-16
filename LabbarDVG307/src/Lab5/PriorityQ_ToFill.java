package Lab5;

import java.lang.reflect.Array;

public class PriorityQ_ToFill<DATA, WEIGHT extends Comparable<WEIGHT>> // Should be implemented as a tree
{
	private Heapish<Node> heap;

	public PriorityQ_ToFill(int capacity) {
		@SuppressWarnings("unchecked")
		Node[] array = (Node[]) Array.newInstance(Node.class, capacity);
		heap = new MinHeap<Node>(array);
	}

	public int size() {
		return heap.size();
	}

	public void insert(DATA data, WEIGHT weight) {
		heap.insert(new Node(data, weight));
	}

	public DATA extract() {
		return heap.extract().getData();
	}

	private class Node implements Comparable<Node> {
		private DATA data;
		private WEIGHT weight;

		public Node(DATA data, WEIGHT weight) {
			this.data = data;
			this.weight = weight;
		}

		public DATA getData() {
			return data;
		}

		public WEIGHT getWeight() {
			return weight;
		}

		public int compareTo(Node other) {
			int result = weight.compareTo(other.getWeight());
			return result;
		}
	}
}
