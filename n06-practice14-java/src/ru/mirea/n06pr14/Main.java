package ru.mirea.n06pr14;

public class Main {

    public static void main(String[] args) {
    	String input = "Ворожейкин Данил";
	    Huffman huffman = new Huffman(input, true);
		System.out.println("Result: " + huffman.encode());
    }
}
