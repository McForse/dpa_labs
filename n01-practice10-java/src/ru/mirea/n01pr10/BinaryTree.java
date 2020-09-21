package ru.mirea.n01pr10;

public class BinaryTree<T extends Comparable<T>> implements Treeable<T> {
	private Node root;

	class Node {
		T value;
		Node left;
		Node right;

		Node(T value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value.toString();
		}
	}

	public BinaryTree() {
	}

	public BinaryTree(T value) {
		this.root = new Node(value);
	}

	@Override
	public void add(T value) {
		root = add(root, value);
	}

	private Node add(Node current, T value) {
		if (current == null) {
			return new Node(value);
		} else if (value.compareTo(current.value) < 0) { // value < current.value
			current.left = add(current.left, value);
		} else if (value.compareTo(current.value) > 0) { // value > current.value
			current.right = add(current.right, value);
		}

		return current;
	}

	@Override
	public boolean contains(T value) {
		return contains(root, value);
	}

	public boolean contains(Node current, T value) {
		if (current == null) {
			return false;
		} else if (value.compareTo(current.value) == 0) { // value == current.value
			return true;
		}

		// value < current.value
		return value.compareTo(current.value) < 0 ? contains(current.left, value) : contains(current.right, value);
	}

	@Override
	public void delete(T value) {
		root = delete(root, value);
	}

	private Node delete(Node current, T value) {
		if (current == null) {
			return null;
		} else if (value == current.value) {
			if (current.left == null && current.right == null) { // No children
				return null;
			} else if (current.right == null) { // Only 1 child (left)
				return current.left;
			} else if (current.left == null) { // Only 1 child (right)
				return current.right;
			}

			// 2 children
			T smallestValue = findSmallestValue(current.left);
			current.value = smallestValue;
			current.left = delete(current.left, smallestValue);
			return current;
		} else if (value.compareTo(current.value) < 0) { // value < current.value
			current.left = delete(current.left, value);
			return current;
		}

		// value > current.value
		current.right = delete(current.right, value);
		return current;
	}

	private T findSmallestValue(Node current) {
		return current.left != null ? findSmallestValue(current.left) : current.value;
	}

	@Override
	public void print() {
		print(root, 0);
	}

	private void print(Node current, int level) {
		if (current != null) {
			print(current.left, level + 1);
			for (int i = 0; i < level; i++) {
				System.out.print("	");
			}
			System.out.println(current);
			print(current.right, level + 1);
		}
	}

	public boolean isEmpty() {
		return root == null;
	}
}
