package ru.mirea.n03pr11;

public class MyHashTable {
	private Node[] table;
	private int capacity;

	class Node {
		Object value;
		Node next;

		public Node(Object value) {
			this.value = value;
		}

		@Override
		public String toString() {
			Node current = this;
			StringBuilder res = new StringBuilder();
			res.append(value.toString());

			while (current.next != null) {
				res.append(" -> ").append(current.next.value);
				current = current.next;
			}

			return res.toString();
		}
	}

	public MyHashTable(int capacity) {
		this.capacity = capacity;
		table = new Node[capacity];

		for (int i = 0; i < capacity; i++) {
			table[i] = null;
		}
	}

	public void add(Object value) {
		int hash = value.hashCode() % capacity;

		if (table[hash] == null) {
			table[hash] = new Node(value);
		} else {
			Node current = table[hash];

			while (current.next != null) {
				current = current.next;
			}

			current.next = new Node(value);
		}
	}

	public boolean contains(Object value) {
		int hash = value.hashCode() % capacity;
		Node current = table[hash];

		while (current != null && !current.value.equals(value)) {
			current = current.next;
		}

		return current != null;
	}

	public boolean remove(Object value) {
		int hash = value.hashCode() % capacity;
		Node current = table[hash];

		if (current == null) {
			return false;
		}

		Node prevEntry = null;

		while (current.next != null && !current.value.equals(value)) {
			prevEntry = current;
			current = current.next;
		}

		if (current.value.equals(value)) {
			if (prevEntry == null) {
				table[hash] = current.next;
			} else {
				prevEntry.next = current.next;
			}

			return true;
		}

		return false;
	}

	public String toString() {
		StringBuilder res = new StringBuilder();

		for (int i = 0; i < capacity; i++) {
			res.append(i).append(": ");

			if (table[i] != null) {
				res.append(table[i].toString());
			} else {
				res.append("[]");
			}

			res.append('\n');
		}

		return res.toString();
	}
}
