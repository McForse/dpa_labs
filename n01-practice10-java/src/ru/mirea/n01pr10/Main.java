package ru.mirea.n01pr10;

public class Main {

    public static void main(String[] args) {
		Treeable<Integer> binaryTree = new BinaryTree<>();
		binaryTree.add(5);
		binaryTree.add(35);
		binaryTree.add(1);
		binaryTree.add(20);
		binaryTree.add(4);
		binaryTree.add(17);
		binaryTree.add(31);
		binaryTree.add(99);
		binaryTree.add(18);
		binaryTree.add(19);
		binaryTree.print();
		System.out.println("\n-----\n");
		binaryTree.delete(4);
		binaryTree.print();
    }
}
