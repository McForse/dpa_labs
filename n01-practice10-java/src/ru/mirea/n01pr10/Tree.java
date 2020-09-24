package ru.mirea.n01pr10;

import java.util.ArrayList;
import java.util.List;

public abstract class Tree<T extends Comparable<T>> implements Treeable<T> {
	protected Node<T> root;

	@Override
	public boolean contains(T value) {
		return contains(root, value);
	}

	protected boolean contains(Node<T> current, T value) {
		if (current == null) {
			return false;
		} else if (value.compareTo(current.value) == 0) { // value == current.value
			return true;
		}

		// value < current.value
		return value.compareTo(current.value) < 0 ? contains(current.left, value) : contains(current.right, value);
	}

	@Override
	public void print() {
		print(root, 0);
	}

	protected void print(Node<T> current, int level) {
		if (current != null) {
			print(current.left, level + 1);

			for (int i = 0; i < level; i++) {
				System.out.print("	");
			}

			System.out.println(current);
			print(current.right, level + 1);
		}
	}

	protected Node<T> findSmallestNode(Node<T> current) {
		return current.left != null ? findSmallestNode(current.left) : current;
	}

	@Override
	public List<T> toArray() {
		ArrayList<T> list = new ArrayList<>();
		infixTraversal(root, list);
		return list;
	}

	protected void infixTraversal(Node<T> current, ArrayList<T> list) {
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
}
