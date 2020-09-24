package ru.mirea.n01pr10;

public class AVLTree<T extends Comparable<T>> extends BinaryTree<T> {

	class AVLNode extends Node<T> {
		int height;

		AVLNode(T value) {
			super(value);
		}
	}

	public AVLTree() {
	}

	public AVLTree(T value) {
		root = new AVLNode(value);
	}

	@Override
	protected Node<T> add(Node<T> current, T value) {
		if (current == null) {
			return new AVLNode(value);
		} else if (value.compareTo(current.value) < 0) { // value < current.value
			current.left = add(current.left, value);
		} else if (value.compareTo(current.value) > 0) { // value > current.value
			current.right = add(current.right, value);
		}

		return rebalance(current);
	}

	@Override
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

		return rebalance(current);
	}

	private Node<T> rotateLeft(Node<T> y) {
		Node<T> x = y.right;
		Node<T> z = x.left;
		x.left = y;
		y.right = z;
		updateHeight((AVLNode) y);
		updateHeight((AVLNode) x);
		return x;
	}

	private Node<T> rotateRight(Node<T> y) {
		Node<T> x = y.left;
		Node<T> z = x.right;
		x.right = y;
		y.left = z;
		updateHeight((AVLNode) y);
		updateHeight((AVLNode) x);
		return x;
	}

	private Node<T> rebalance(Node<T> z) {
		updateHeight((AVLNode) z);
		int balance = getBalance((AVLNode) z);

		if (balance > 1) {
			if (getHeight((AVLNode) z.right.right) <= getHeight((AVLNode) z.right.left)) {
				z.right = rotateRight(z.right);
			}

			z = rotateLeft(z);
		} else if (balance < -1) {
			if (getHeight((AVLNode) z.left.left) <= getHeight((AVLNode) z.left.right)) {
				z.left = rotateLeft(z.left);
			}

			z = rotateRight(z);
		}

		return z;
	}

	private int getHeight(AVLNode current) {
		return current != null ? current.height : -1;
	}

	private void updateHeight(AVLNode current) {
		current.height = 1 + Math.max(getHeight((AVLNode) current.left), getHeight((AVLNode) current.right));
	}

	private int getBalance(AVLNode current) {
		return current == null ? 0 : getHeight((AVLNode) current.right) - getHeight((AVLNode) current.left);
	}
}