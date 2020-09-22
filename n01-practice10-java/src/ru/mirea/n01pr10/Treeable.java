package ru.mirea.n01pr10;

public interface Treeable<T> {
	void add(T value);
	boolean contains(T value);
	void delete(T value);
	void print();
	boolean isEmpty();
}
