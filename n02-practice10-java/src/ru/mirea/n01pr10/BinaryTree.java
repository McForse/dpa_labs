package ru.mirea.n01pr10;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

		return current;
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
			print(current.right, level + 1);

			for (int i = 0; i < level; i++) {
				System.out.print("	");
			}

			System.out.println(current);
			print(current.left, level + 1);
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

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input nodes:");
		String str = sc.nextLine();
		BinaryTree<Integer> tree = new BinaryTree<>();

		while (!str.equals("stop")) {
			try {
				tree.add(Integer.parseInt(str));
			} catch (Exception e) {
				System.out.println("Incorrect input");
				str = sc.nextLine();
				continue;
			}

			tree.print();
			str = sc.nextLine();
		}
	}
}
