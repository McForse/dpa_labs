package ru.mirea.n01pr9;

public interface Queueable<T> {
	boolean add(T item);
	T remove();
	T peek();
	boolean isEmpty();
}
