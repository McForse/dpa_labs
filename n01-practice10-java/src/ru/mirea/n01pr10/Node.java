package ru.mirea.n01pr10;

public abstract class AbstractNode<T> {
	T value;
	AbstractNode<T> left;
	AbstractNode<T> right;

	public AbstractNode() {
	}

	public AbstractNode(T value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value.toString();
	}
}
