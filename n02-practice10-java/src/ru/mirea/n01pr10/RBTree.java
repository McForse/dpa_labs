package ru.mirea.n01pr10;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
			color = Color.RED;
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
		Node current = new Node(value);

		if (root == null) {
			root = current;
			current.parent = null;
		} else {
			Node p = root;
			Node q = null;

			while (p != null) {
				q = p;

				if (p.value.compareTo(current.value) < 0) { // p.value < t.value
					p = p.right;
				} else {
					p = p.left;
				}
			}

			current.parent = q;
			// добавляем новый элемент красного цвета
			if (q.value.compareTo(current.value) < 0) { // q.value < t.value
				q.right = current;
			} else {
				q.left = current;
			}
		}

		fixInsertion(current); // проверяем, не нарушены ли свойства красно-черного дерева
	}

	private void fixInsertion(Node current) {
		if (current == root) {
			current.color = Color.BLACK;
			return;
		}
		Node parent = current.parent;

		while (parent != null && parent.color == Color.RED) { // нарушается свойство (У красного узла родительский узел — чёрный)
			Node grandfather = getGrandparent(current);
			Node uncle = getUncle(current);

			if (grandfather == null) {
				return;
			}

			if (current.parent == grandfather.left) { // "отец" — левый ребенок
				if (uncle != null && uncle.color == Color.RED) { // есть "дядя" и он красный
					parent.color = Color.BLACK;
					uncle.color = Color.BLACK;
					grandfather.color = Color.RED;
					current = grandfather;
				} else { // случай, когда нет "дяди"
					if (current == parent.right) { // current — правый сын
						current = parent;
						grandfather.left = rotateLeft(current);
					}

					current.parent.color = Color.BLACK;
					grandfather.color = Color.RED;
					Node gp = grandfather.parent;

					if (gp != null) {
						gp = rotateRight(grandfather);
						grandfather = getGrandparent(current);
					} else {
						root = rotateRight(grandfather);
					}

					grandfather.left = gp;
				}
			} else { // "отец" — правый ребенок
				if (uncle != null && uncle.color == Color.RED) { // есть "дядя" и он красный
					parent.color = Color.BLACK;
					uncle.color = Color.BLACK;
					grandfather.color = Color.RED;
					current = grandfather;
				} else { // нет "дяди"
					if (current == parent.left) { // current — левый ребенок
						current = current.parent;
						grandfather.right = rotateRight(current);
					}

					current.parent.color = Color.BLACK;
					grandfather.color = Color.RED;
					Node gp = grandfather.parent;

					if (gp != null) {
						gp = rotateLeft(grandfather);
						grandfather = getGrandparent(current);
					} else {
						root = rotateLeft(grandfather);
					}

					grandfather.right = gp;
				}
			}

			parent = current.parent;
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
	//TODO: other cases
	public void delete(T value) {
		Node current = root;

		while (current.value != value) {
			if (current.value.compareTo(value) < 0) { // current.value < value
				current = current.right;
			} else {
				current = current.left;
			}
		}

		// у current нет детей
		if (current.left == null && current.right == null) {
			// current — корень
			if (current == root) {
				root = null;
			} else {
				// ссылку на current у "отца" меняем на null
				Node parent = current.parent;

				// current в левой ветке "отца"
				if (current == parent.left) {
					parent.left = null;
				} else { // current в правой ветке "отца"
					parent.right = null;
				}
			}
		}
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

			System.out.println(current.color == Color.RED ? ANSI_RED + current : ANSI_RESET + current);
			print(current.left, level + 1);
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
		Scanner sc = new Scanner(System.in);
		System.out.println("Input nodes:");
		String str = sc.nextLine();
		RBTree<Integer> tree = new RBTree<>();

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

	// Testing
	/*public static void main(String[] args) {
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
		tree.add(18);
		tree.add(16);
		tree.add(14);
		tree.add(3);
		tree.print();
	}*/
}
