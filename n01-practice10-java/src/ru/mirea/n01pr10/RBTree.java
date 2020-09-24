package ru.mirea.n01pr10;

import java.util.ArrayList;
import java.util.List;


public class RBTree<T extends Comparable<T>> implements Treeable<T> {
	private Node root;

	enum Color {
		RED, BLACK
	}

	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_RESET = "\u001B[0m";

	class Node {
		T value;
		Color color;
		Node left;
		Node right;
		Node parent;

		Node(T value) {
			this.value = value;
			color = Color.BLACK;
		}

		Node(T value, Color color) {
			this.value = value;
			this.color = color;
		}

		@Override
		public String toString() {
			return value.toString();
		}
	}

	@Override
	public void add(T value) {
		Node t = new Node(value, Color.RED);

		if (root == null) {
			root = t;
			t.parent = null;
		} else {
			Node p = root;
			Node q = null;

			while (p != null) {
				q = p;

				if (p.value.compareTo(t.value) < 0) { // p.value < t.value
					p = p.right;
				} else {
					p = p.left;
				}
			}

			t.parent = q;
			// добавляем новый элемент красного цвета
			if (q.value.compareTo(t.value) < 0) { // q.value < t.value
				q.right = t;
			} else {
				q.left = t;
			}
		}

		fixInsertion(t); // проверяем, не нарушены ли свойства красно-черного дерева
	}

	private void fixInsertion(Node t) {
		if (t == root) {
			t.color = Color.BLACK;
			return;
		}
		Node parent = t.parent;

		// далее все предки упоминаются относительно t
		while (parent != null && parent.color == Color.RED) { // нарушается свойство 3
			Node grandfather = getGrandparent(t);

			if (grandfather == null) {
				return;
			}

			if (t.parent == grandfather.left) { // "отец" — левый ребенок
				Node uncle = getUncle(t);

				if (uncle != null) { // есть "дядя"
					if (uncle.color == Color.RED) { //"дядя" красный
						parent.color = Color.BLACK;
						uncle.color = Color.BLACK;
						grandfather.color = Color.RED;
						t = grandfather;
					}
				} else { // случай, когда нет "дяди"
					if (t == parent.right) { // t — правый сын
						t = parent;
						grandfather.left = rotateLeft(t);
					}

					t.parent.color = Color.BLACK;
					grandfather.color = Color.RED;

					Node gp = grandfather.parent;
					if (gp != null) {
						gp = rotateRight(grandfather);
						grandfather = getGrandparent(t);
					} else {
						root = rotateRight(grandfather);
					}
					grandfather.left = gp;
				}
			} else { // "отец" — правый ребенок
				Node uncle = getUncle(t);

				if (uncle != null) { // есть "дядя"
					if (uncle.color == Color.RED) { //"дядя" красный
						parent.color = Color.BLACK;
						uncle.color = Color.BLACK;
						grandfather.color = Color.RED;
						t = grandfather;
					}
				} else { // нет "дяди"
					if (t == parent.left) { // t — левый ребенок
						t = t.parent;
						grandfather.right = rotateRight(t);
					}

					t.parent.color = Color.BLACK;
					grandfather.color = Color.RED;

					Node gp = grandfather.parent;
					if (gp != null) {
						gp = rotateLeft(grandfather);
						grandfather = getGrandparent(t);
					} else {
						root = rotateLeft(grandfather);
					}
					grandfather.right = gp;
				}
			}

			parent = t.parent;
		}

		root.color = Color.BLACK; // восстанавливаем свойство корня
	}

	private Node getGrandparent(Node n) {
		if ((n != null) && (n.parent != null)) {
			return n.parent.parent;
		}

		return null;
	}

	private Node getUncle(Node n) {
		Node g = getGrandparent(n);

		if (g == null) {
			return null;
		} else if (n.parent == g.left) { // Uncle in the right node
			return g.right;
		} else { // Uncle in the left node
			return g.left;
		}
	}

	private Node rotateLeft(Node y) {
		Node x = y.right;
		Node z = x.left;
		x.left = y;
		x.parent = y.parent;
		y.parent = x;
		y.right = z;
		return x;
	}

	private Node rotateRight(Node y) {
		Node x = y.left;
		Node z = x.right;
		x.right = y;
		x.parent = y.parent;
		y.parent = x;
		y.left = z;
		return x;
	}

	@Override
	public boolean contains(T value) {
		return false;
	}

	@Override
	public void delete(T value) {

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

			System.out.println(current.color == Color.RED ? ANSI_RED + current : ANSI_RESET + current);
			print(current.right, level + 1);
		}
	}

	@Override
	public List<T> toArray() {
		ArrayList<T> list = new ArrayList<>();
		infixTraversal(root, list);
		return list;
	}

	private void infixTraversal(Node current, List<T> list) {
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
		RBTree<Integer> tree = new RBTree<>();
		tree.add(10);
		tree.add(5);
		tree.add(6);
		tree.add(15);
		tree.add(7);
		tree.add(13);
		tree.add(4);
		tree.add(1);
		tree.add(20);
		tree.add(2);
		tree.add(50);
		tree.add(8);
		tree.add(30);
		tree.add(13);
		/*tree.insert(18);
		tree.insert(16);
		tree.insert(14);
		tree.insert(3);*/
		tree.print();
		//tree.test();
	}
}
