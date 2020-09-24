package ru.mirea.n01pr10;

import java.util.ArrayList;
import java.util.List;

public class AVLTree<T extends Comparable<T>> implements Treeable<T> {
	private Node root;

	class Node {
		T value;
		int height;
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

	public AVLTree() {
	}

	public AVLTree(T value) {
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

		return rebalance(current);
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
				Node smallestNode = findSmallestNode(current.right);
				current.value = smallestNode.value;
				current.right = delete(current.right, smallestNode.value);
			}
		}

		return rebalance(current);
	}

	private Node findSmallestNode(Node current) {
		return current.left != null ? findSmallestNode(current.left) : current;
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

	@Override
	public List<T> toArray() {
		ArrayList<T> list = new ArrayList<>();
		infixTraversal(root, list);
		return list;
	}

	private void infixTraversal(Node current, ArrayList<T> list) {
		if (current.left != null) {
			infixTraversal(current.left, list);
		}

		list.add(current.value);

		if (current.right != null) {
			infixTraversal(current.right, list);
		}
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	private Node rebalance(Node z) {
		updateHeight(z);
		int balance = getBalance(z);

		if (balance > 1) {
			if (getHeight(z.right.right) <= getHeight(z.right.left)) {
				z.right = rotateRight(z.right);
			}

			z = rotateLeft(z);
		} else if (balance < -1) {
			if (getHeight(z.left.left) <= getHeight(z.left.right)) {
				z.left = rotateLeft(z.left);
			}

			z = rotateRight(z);
		}

		return z;
	}

	private Node rotateLeft(Node y) {
		Node x = y.right;
		Node z = x.left;
		x.left = y;
		y.right = z;
		updateHeight(y);
		updateHeight(x);
		return x;
	}

	private Node rotateRight(Node y) {
		Node x = y.left;
		Node z = x.right;
		x.right = y;
		y.left = z;
		updateHeight(y);
		updateHeight(x);
		return x;
	}

	private int getHeight(Node current) {
		return current != null ? current.height : -1;
	}

	private void updateHeight(Node current) {
		current.height = 1 + Math.max(getHeight(current.left), getHeight(current.right));
	}

	private int getBalance(Node current) {
		return (current == null) ? 0 : getHeight(current.right) - getHeight(current.left);
	}
}
