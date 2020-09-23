package ru.mirea.n01pr9.queue;

import ru.mirea.n01pr9.Queueable;

public class Queue<T> implements Queueable<T> {
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

	@Override
	public boolean add(T item) {
		if (size < capacity) {
			Node node = new Node(item);

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

	@Override
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

	@Override
	public T peek() {
		return first.value;
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	public boolean isFull() {
		return size == capacity;
	}
}
