package ru.mirea.n03pr11;

/**
 * Реализация хеш-таблицы.
 * Разрешение коллизий методом цепочек
 */
public class MyHashTable {
	private static final int DEFAULT_CAPACITY = 16;
	private Node[] table;
	private int capacity;

	/**
	 * Структура элемента в таблице
	 */
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

	/**
	 * Конструктор без параметров
	 */
	public MyHashTable() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Конструктор с установкой размера таблицы
	 */
	public MyHashTable(int capacity) {
		this.capacity = capacity;
		table = new Node[capacity];

		for (int i = 0; i < capacity; i++) {
			table[i] = null;
		}
	}

	/**
	 * Добавление элемента в таблицу
	 */
	public void add(Object value) {
		int hash = hash(value);

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

	/**
	 * Проверка содержание элемента в таблице
	 */
	public boolean contains(Object value) {
		int hash = hash(value);
		Node current = table[hash];

		while (current != null && !current.value.equals(value)) {
			current = current.next;
		}

		return current != null;
	}

	/**
	 * Удаление элемента из таблицы
	 */
	public boolean remove(Object value) {
		int hash = hash(value);
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

	private int hash(Object value) {
		return (value.hashCode() * 37) % capacity;
	}

	/**
	 * Вывод хеш-таблицы
	 */
	public void print() {
		System.out.println(toString());
	}

	/**
	 * Преобразование хеш-таблицы в строку
	 */
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
