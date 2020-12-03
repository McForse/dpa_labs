package ru.mirea.n06pr14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Разработать процедуру сжатия данных на основе жадного алгоритма
 * Хаффмана.
 */
public class Main {

	public static void main(String[] args) {
		String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";

		// Вычисляем частоты встречаемости каждого символа в тексте
		Map<Character, Integer> frequency = countFrequency(text);

		// Генерация списка листьев дерева
		ArrayList<Node> nodes = new ArrayList<>();
		for (Character c : frequency.keySet()) {
			nodes.add(new Node(c, frequency.get(c)));
		}

		// Построения бинарного дерева Хаффмана
		Node tree = huffman(nodes);

		// Кодируем текст
		String encoded = huffmanEncode(text, tree, frequency);

		System.out.println("Исходная строка: " + text);
		System.out.println("Размер исходной строки: " + text.getBytes().length * 8 + " бит");
		System.out.println("Размер сжатой строки: " + encoded.length() + " бит");
		System.out.println("Зашифрованная строка: " + encoded);

		// Декодируем сжатую информацию обратно
		String decoded = huffmanDecode(encoded, tree);
		System.out.println("Расшифровано: " + decoded);
	}

	/**
	 * Функция подсчёт частоты встречаемости каждого символа
	 * @param text исходный текст
	 * @return ассоциативный массив
	 */
	private static Map<Character, Integer> countFrequency(String text) {
		Map<Character, Integer> freqMap = new TreeMap<>();

		for (char c : text.toCharArray()) {
			if (freqMap.containsKey(c)) {
				freqMap.merge(c, 1, Integer::sum);
			} else {
				freqMap.put(c, 1);
			}
		}

		return freqMap;
	}

	/***
	 * Функция построения бинарного дерева Хаффмана
	 * @param nodes список узлов
	 * @return построенное дерево
	 */
	private static Node huffman(ArrayList<Node> nodes) {
		while (nodes.size() > 1) {
			Collections.sort(nodes);
			Node left = nodes.remove(nodes.size() - 1);
			Node right = nodes.remove(nodes.size() - 1);

			Node parent = new Node(null, right.weight + left.weight, left, right);
			nodes.add(parent);
		}

		return nodes.get(0);
	}

	/**
	 * Функция кодирования
	 * @param text исходное сообщение
	 * @return закодированное сообщение
	 */
	private static String huffmanEncode(String text, Node tree, Map<Character, Integer> frequency) {
		// Генерируем таблицу префиксных кодов для кодируемых символов с помощью кодового дерева
		Map<Character, String> codes = new TreeMap<>();
		for (Character c : frequency.keySet()) {
			codes.put(c, tree.getCharacterCode(c, ""));
		}

		System.out.println("Таблица префиксных кодов: " + codes.toString());

		// Кодируем текст, заменяем сиволы соответствующими кодами
		StringBuilder encoded = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			encoded.append(codes.get(text.charAt(i)));
		}

		return encoded.toString();
	}

	/**
	 * Функция декодирования
	 * @param encoded закодированное сообщение
	 * @param tree дерево Хаффмана
	 * @return декодированное сообщение
	 */
	private static String huffmanDecode(String encoded, Node tree) {
		StringBuilder decoded = new StringBuilder();
		Node node = tree;

		for (int i = 0; i < encoded.length(); i++) {
			node = encoded.charAt(i) == '0' ? node.left : node.right;

			if (node.content != null) {
				decoded.append(node.content);
				node = tree;
			}
		}

		return decoded.toString();
	}

	private static class Node implements Comparable<Node> {
		Character content;
		int weight;

		Node left;
		Node right;

		public Node(Character content, int weight) {
			this.content = content;
			this.weight = weight;
		}

		public Node(Character content, int weight, Node left, Node right) {
			this.content = content;
			this.weight = weight;
			this.left = left;
			this.right = right;
		}

		@Override
		public int compareTo(Node o) {
			return o.weight - weight;
		}

		public String getCharacterCode(Character ch, String parentPath) {
			if (content == ch) {
				return parentPath;
			} else {
				if (left != null) {
					String path = left.getCharacterCode(ch, parentPath + 0);
					if (path != null) return path;
				}

				if (right != null) {
					return right.getCharacterCode(ch, parentPath + 1);
				}
			}

			return null;
		}
	}
}
