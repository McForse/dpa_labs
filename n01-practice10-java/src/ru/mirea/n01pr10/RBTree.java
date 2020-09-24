package ru.mirea.n01pr10;

public class RBTree<T extends Comparable<T>> extends Tree<T> {

	enum Color {
		RED, BLACK
	}

	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_RESET = "\u001B[0m";

	class RBNode extends Node<T> {
		T value;
		Node<T> parent;
		Color color;

		RBNode(T value) {
			super(value);
			color = Color.BLACK;
		}

		RBNode(T value, Color color) {
			super(value);
			this.color = color;
		}
	}

	@Override
	public void add(T value) {
		Node<T> t = new RBNode(value, Color.RED);

		if (root == null) {
			root = t;
			((RBNode) t).parent = null;
		} else {
			Node<T> p = root;
			Node<T> q = null;

			while (p != null) {
				q = p;

				if (p.value.compareTo(t.value) < 0) { // p.value < t.value
					p = p.right;
				} else {
					p = p.left;
				}
			}

			((RBNode) t).parent = q;
			// добавляем новый элемент красного цвета
			if (q.value.compareTo(t.value) < 0) { // q.value < t.value
				q.right = t;
			} else {
				q.left = t;
			}
		}

		fixInsertion(t); // проверяем, не нарушены ли свойства красно-черного дерева
	}

	private void fixInsertion(Node<T> t) {
		if (t == root) {
			((RBNode) t).color = Color.BLACK;
			return;
		}
		Node<T> parent = ((RBNode) t).parent;

		// далее все предки упоминаются относительно t
		while (parent != null && ((RBNode) parent).color == Color.RED) { // нарушается свойство 3
			Node<T> grandfather = getGrandparent(t);

			if (grandfather == null) {
				return;
			}

			if (((RBNode) t).parent == grandfather.left) { // "отец" — левый ребенок
				Node<T> uncle = getUncle(t);

				if (uncle != null) { // есть "дядя"
					if (((RBNode) uncle).color == Color.RED) { //"дядя" красный
						((RBNode) parent).color = Color.BLACK;
						((RBNode) uncle).color = Color.BLACK;
						((RBNode) grandfather).color = Color.RED;
						t = grandfather;
					}
				} else { // случай, когда нет "дяди"
					if (t == parent.right) { // t — правый сын
						t = parent;
						grandfather.left = rotateLeft(t);
					}

					((RBNode) ((RBNode) t).parent).color = Color.BLACK;
					((RBNode) grandfather).color = Color.RED;

					Node<T> gp = ((RBNode) grandfather).parent;
					if (gp != null) {
						gp = rotateRight(grandfather);
						grandfather = getGrandparent(t);
					} else {
						root = rotateRight(grandfather);
					}
					grandfather.left = gp;
				}
			} else { // "отец" — правый ребенок
				Node<T> uncle = getUncle(t);

				if (uncle != null) { // есть "дядя"
					if (((RBNode) uncle).color == Color.RED) { //"дядя" красный
						((RBNode) parent).color = Color.BLACK;
						((RBNode) uncle).color = Color.BLACK;
						((RBNode) grandfather).color = Color.RED;
						t = grandfather;
					}
				} else { // нет "дяди"
					if (t == parent.left) { // t — левый ребенок
						t = ((RBNode) t).parent;
						grandfather.right = rotateRight(t);
					}

					((RBNode) ((RBNode) t).parent).color = Color.BLACK;
					((RBNode) grandfather).color = Color.RED;

					Node<T> gp = ((RBNode) grandfather).parent;
					if (gp != null) {
						gp = rotateLeft(grandfather);
						grandfather = getGrandparent(t);
					} else {
						root = rotateLeft(grandfather);
					}
					grandfather.right = gp;
				}
			}

			parent = ((RBNode) t).parent;
		}

		((RBNode) root).color = Color.BLACK; // восстанавливаем свойство корня
	}

	private Node<T> getGrandparent(Node<T> n) {
		if ((n != null) && ((RBNode) n).parent != null) {
			return ((RBNode) ((RBNode) n).parent).parent;
		}

		return null;
	}

	private Node<T> getUncle(Node<T> n) {
		Node<T> g = getGrandparent(n);

		if (g == null) {
			return null;
		} else if (((RBNode) n).parent == g.left) { // Uncle in the right node
			return g.right;
		} else { // Uncle in the left node
			return g.left;
		}
	}

	private Node<T> rotateLeft(Node<T> y) {
		Node<T> x = y.right;
		Node<T> z = x.left;
		x.left = y;
		((RBNode) x).parent = ((RBNode) y).parent;
		((RBNode) y).parent = x;
		y.right = z;
		return x;
	}

	private Node<T> rotateRight(Node<T> y) {
		Node<T> x = y.left;
		Node<T> z = x.right;
		x.right = y;
		((RBNode) x).parent = ((RBNode) y).parent;
		((RBNode) y).parent = x;
		y.left = z;
		return x;
	}

	@Override
	public void delete(T value) {

	}

	@Override
	protected void print(Node<T> current, int level) {
		if (current != null) {
			print(current.left, level + 1);

			for (int i = 0; i < level; i++) {
				System.out.print("	");
			}

			System.out.println(((RBNode) current).color == Color.RED ? ANSI_RED + current : ANSI_RESET + current);
			print(current.right, level + 1);
		}
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
	}
}