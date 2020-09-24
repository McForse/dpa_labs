package ru.mirea.n01pr10;

public class Node<T> {
	protected T value;
	protected Node<T> left;
	protected Node<T> right;

	public Node(T value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value.toString();
	}
}
