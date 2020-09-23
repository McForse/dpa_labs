package ru.mirea.n01pr10;

import java.util.List;

public interface Treeable<T> {
	void add(T value);
	boolean contains(T value);
	void delete(T value);
	void print();
	List<T> toArray();
	boolean isEmpty();

	default void add(T... values) {
		for (T value : values) {
			add(value);
		}
	}
}
