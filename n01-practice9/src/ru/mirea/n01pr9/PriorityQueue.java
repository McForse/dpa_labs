package ru.mirea.n01pr9;

import java.util.Comparator;

public class PriorityQueue<T> extends Queue<T> {
	private final Comparator<T> comparator;

	public PriorityQueue(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public PriorityQueue(int capacity, Comparator<T> comparator) {
		super(capacity);
		this.comparator = comparator;
	}

	@Override
	public boolean add(T object) {
		if (size < capacity) {
			Node node = new Node(object);

			if (first == null) {
				last = node;
				first = last;
			} else {
				Node temp = first;

				while (temp != null && comparator.compare(temp.value, object) >= 0) {
					temp = temp.next;
				}

				if (temp == null) {
					last.next = node;
					last = last.next;
				} else {
					T bufValue = temp.value;
					Node bufNext = temp.next;
					temp.value = object;
					temp.next = new Node(bufNext, bufValue);
				}
			}

			size++;
			return true;
		}

		return false;
	}
}
