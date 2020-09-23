package ru.mirea.n01pr9.arraylist;

import ru.mirea.n01pr9.Queueable;

import java.util.Comparator;

public class PriorityQueue<T> implements Queueable<T> {
	private final Comparator<T> comparator;
	protected MyArrayList<T> data;

	public PriorityQueue(Comparator<T> comparator) {
		this.comparator = comparator;
		data = new MyArrayList<>();
	}

	@Override
	public boolean add(T item) {
		if (data.isEmpty()) {
			data.add(item);
			return true;
		}

		int index = 0;
		while (index < data.size() && comparator.compare(data.get(index), item) >= 0) {
			index++;
		}

		if (index == data.size() - 1) {
			data.add(item);
			return true;
		}

		data.add(index, item);
		return true;
	}

	@Override
	public T remove() {
		if (data.size() < 1) {
			return null;
		}

		T item = data.get(0);
		data.remove(0);
		return item;
	}

	@Override
	public T peek() {
		if (data.size() < 1) {
			return null;
		}

		return data.get(0);
	}

	@Override
	public boolean isEmpty() {
		return data.isEmpty();
	}
}
