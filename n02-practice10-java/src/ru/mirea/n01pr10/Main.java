package ru.mirea.n01pr10;

import java.util.Random;

public class Main {

	public static final String SEPARATOR = "-----";

    public static void main(String[] args) {
    	System.out.println("Binary tree:");
		Treeable<Integer> binaryTree = new BinaryTree<>();
		fillTree(binaryTree);
		binaryTree.print();
		System.out.println("\n" + SEPARATOR);
		System.out.println("Delete 4 in binary tree:");
		binaryTree.delete(4);
		binaryTree.print();
		System.out.println("\n" + SEPARATOR);
		System.out.println(binaryTree.toArray());

		System.out.println("\n" + SEPARATOR);
		System.out.println("AVL tree:");
		Treeable<Integer> avlTree = new AVLTree<>();;
		fillTree(avlTree);
		avlTree.print();
		System.out.println("\n" + SEPARATOR);
		System.out.println("Delete 5 in avl tree:");
		avlTree.delete(5);
		avlTree.print();
		System.out.println("\n" + SEPARATOR);
		System.out.println(avlTree.toArray());
    }

    public static void fillTree(Treeable<Integer> tree) {
		tree.add(5);
		tree.add(35);
		tree.add(1);
		tree.add(20);
		tree.add(4);
		tree.add(17);
		tree.add(31);
		tree.add(99);
		tree.add(18);
		tree.add(19);
	}

	public static void randomFillTree(Treeable<Integer> tree, int n) {
		Random random = new Random(System.currentTimeMillis());

		for (int i = 0; i < n; i++) {
			tree.add(random.nextInt(20) + 1);
		}
	}
}
