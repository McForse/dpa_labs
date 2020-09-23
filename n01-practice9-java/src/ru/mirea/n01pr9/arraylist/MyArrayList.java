package ru.mirea.n01pr9.arraylist;

import java.util.Arrays;
import java.util.Collection;

public class MyArrayList<T> {
	private static final int DEFAULT_CAPACITY = 10;
	private Object[] data;
	private int size;

	public MyArrayList() {
		data = new Object[DEFAULT_CAPACITY];
	}

	public MyArrayList(int initialCapacity) {
		if (initialCapacity > 0) {
			this.data = new Object[initialCapacity];
		} else {
			throw new IllegalArgumentException("Illegal capacity: " + initialCapacity);
		}
	}

	public MyArrayList(Object[] array) {
		data = array;
		size = data.length;
	}

	public MyArrayList(Collection<? extends T> c) {
		data = c.toArray();
		size = data.length;
	}

	@SafeVarargs
	public static <T> MyArrayList<T> of(T... elements) {
		return new MyArrayList<T>(Arrays.stream(elements).toArray());
	}

	public void add(T element) {
		checkCapacity();
		data[size++] = element;
	}

	public void add(int index, T element) {
		checkCapacity();
		System.arraycopy(data, index, data, index + 1, size - index);
		data[index] = element;
		size++;
	}

	public void addAll(Collection<? extends T> list) {
		Object[] addList = list.toArray();
		int newCount = addList.length;
		expand(size + newCount);
		System.arraycopy(addList, 0, data, size, newCount);
		size += newCount;
	}

	public void addAll(MyArrayList<? extends T> list) {
		Object[] addList = list.toArray();
		int newCount = addList.length;
		expand(size + newCount);
		System.arraycopy(addList, 0, data, size, newCount);
		size += newCount;
	}

	public void remove(int index) {
		if (size - index >= 0) {
			System.arraycopy(data, index + 1, data, index, size - index - 1);
		}

		data[--size] = null;
	}

	@SuppressWarnings("unchecked")
	public T get(int index) {
		if (index >= size) {
			throw new ArrayIndexOutOfBoundsException();
		}

		return (T) data[index];
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	private void checkCapacity() {
		if (size >= data.length - 1) {
			expand(data.length + DEFAULT_CAPACITY);
		}
	}

	private void expand(int len) {
		Object[] temp = new Object[len + DEFAULT_CAPACITY];
		System.arraycopy(data, 0, temp, 0, size);
		data = temp;
	}

	public Object[] toArray() {
		return Arrays.copyOf(data, size);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("[");
		stringBuilder.append(data[0]);
		for (int i = 1; i < size; i++) {
			stringBuilder.append(", ").append(data[i]);
		}
		stringBuilder.append("]");
		return stringBuilder.toString();
	}
}