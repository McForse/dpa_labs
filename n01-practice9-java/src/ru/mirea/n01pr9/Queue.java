package ru.mirea.n01pr9;

public class Queue<T> {
	protected Node first, last;
	protected int size;
	protected final int capacity;

	public class Node {
		public Node next;
		public T value;

		public Node(T value) {
			this.value = value;
		}

		public Node(Node next, T value) {
			this.next = next;
			this.value = value;
		}
	}

	public Queue() {
		capacity = 100;
	}

	public Queue(int capacity) {
		this.capacity = capacity;
	}

	public boolean add(T object) {
		if (size < capacity) {
			Node node = new Node(object);

			if (first == null) {
				last = node;
				first = last;
			} else {
				last.next = node;
				last = last.next;
			}

			size++;
			return true;
		}

		return false;
	}

	public T remove() {
		if (first == null) {
			return null;
		} else {
			Node temp = first;

			if (first == last) {
				first = null;
				last = null;
			} else {
				first = first.next;
			}

			return temp.value;
		}
	}

	public T peek() {
		return first.value;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public boolean isFull() {
		return size == capacity;
	}
}
