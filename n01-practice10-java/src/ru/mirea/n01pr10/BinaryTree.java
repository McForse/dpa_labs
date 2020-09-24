package ru.mirea.n01pr10;

public class BinaryTree<T extends Comparable<T>> extends Tree<T> {

	public BinaryTree() {
	}

	public BinaryTree(T value) {
		this.root = new Node<>(value);
	}

	@Override
	public void add(T value) {
		root = add(root, value);
	}

	protected Node<T> add(Node<T> current, T value) {
		if (current == null) {
			return new Node<>(value);
		} else if (value.compareTo(current.value) < 0) { // value < current.value
			current.left = add(current.left, value);
		} else if (value.compareTo(current.value) > 0) { // value > current.value
			current.right = add(current.right, value);
		}

		return current;
	}

	@Override
	public void delete(T value) {
		root = delete(root, value);
	}

	protected Node<T> delete(Node<T> current, T value) {
		if (current == null) {
			return null;
		} else if (current.value.compareTo(value) > 0) { // current.value > value
			current.left = delete(current.left, value);
		} else if (current.value.compareTo(value) < 0) { // current.value < value
			current.right = delete(current.right, value);
		} else { // value == current.value
			if (current.left == null && current.right == null) { // No children
				return null;
			} else if (current.right == null) { // Only 1 child (left)
				current = current.left;
			} else if (current.left == null) { // Only 1 child (right)
				current = current.right;
			} else { // 2 children
				Node<T> smallestNode = findSmallestNode(current.right);
				current.value = smallestNode.value;
				current.right = delete(current.right, smallestNode.value);
			}
		}

		return current;
	}
}