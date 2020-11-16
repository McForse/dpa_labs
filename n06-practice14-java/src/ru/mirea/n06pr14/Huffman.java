package ru.mirea.n06pr14;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {
	private String input;
	private boolean debug = false;

	public Huffman(String input) {
		this.input = input;
	}

	public Huffman(String input, boolean debug) {
		this.input = input;
		this.debug = debug;
	}

	private class Node implements Comparable<Node> {
		final int sum;
		String code;

		public Node(int sum) {
			this.sum = sum;
		}

		void buildCode(String code) {
			this.code = code;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(sum, o.sum);
		}
	}

	private class InternalNode extends Node {
		Node left;
		Node right;


		public InternalNode(Node left, Node right) {
			super(left.sum + right.sum);
			this.left = left;
			this.right = right;
		}

		@Override
		void buildCode(String code) {
			super.buildCode(code);
			left.buildCode(code + "0");
			right.buildCode(code + "1");
		}
	}

	private class LeafNode extends Node {
		char symbol;

		public LeafNode(char symbol, int count) {
			super(count);
			this.symbol = symbol;
		}
	}

	public String encode() {
		Map<Character, Integer> count = new HashMap<>();

		for (char c : input.toCharArray()) {
			if (count.containsKey(c)) {
				count.merge(c, 1, Integer::sum);
			} else {
				count.put(c, 1);
			}
		}

		Map<Character, Node> charNode = new HashMap<>();
		PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

		for (Map.Entry<Character, Integer> entry : count.entrySet()) {
			LeafNode node = new LeafNode(entry.getKey(), entry.getValue());
			charNode.put(entry.getKey(), node);
			priorityQueue.add(node);
		}

		while (priorityQueue.size() > 1) {
			Node first = priorityQueue.poll();
			Node second = priorityQueue.poll();
			InternalNode node = new InternalNode(first, second);
			priorityQueue.add(node);
		}

		Node root = priorityQueue.poll();

		if (count.size() == 1) {
			root.code = "0";
		}

		root.buildCode("");
		StringBuilder encoded = new StringBuilder();

		for (char c : input.toCharArray()) {
			encoded.append(charNode.get(c).code);
		}

		if (debug) {
			System.out.println("Symbol rate:");
			count.forEach((k, v) -> System.out.println(k + ": " + v));
			System.out.println("Symbol codes:");
			charNode.forEach((k, v) -> System.out.println(k + ": " + v.code));
		}

		return encoded.toString();
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}
}
