package ru.mirea.n01pr10;

public class Main {

    public static void main(String[] args) {
    	System.out.println("Binary tree:");
		Treeable<Integer> binaryTree = new BinaryTree<>();
		fillTree(binaryTree);
		binaryTree.print();
		System.out.println("\n-----");
		System.out.println("Delete 4 in binary tree:");
		binaryTree.delete(4);
		binaryTree.print();

		System.out.println("\n-----");
		System.out.println("AVL tree:");
		Treeable<Integer> avlTree = new AVLTree<>();;
		fillTree(avlTree);
		avlTree.print();
		System.out.println("\n-----");
		System.out.println("Delete 5 in avl tree:");
		avlTree.delete(5);
		avlTree.print();
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
}
