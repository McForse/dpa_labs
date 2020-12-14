package ru.mirea.n08pr16;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Класс рюкзака
 */
public class Backpack {
	private final int capacity;
	private List<Item> items;

	/**
	 * Конструктор с установкой вместимось рюкзака
	 * @param capacity вместимость рюкзака
	 */
	public Backpack(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * Конструктор с установкой вместимось рюкзака и предметов в нём
	 * @param capacity вместимость рюкзака
	 * @param items список предметов
	 */
	public Backpack(int capacity, List<Item> items) throws Exception {
		this.capacity = capacity;
		if (items.size() > capacity) throw new Exception("Количесво предметов больше чем размер рюкзака!");
		this.items = items;
	}

	/**
	 * Функция добавления предмета в рюкзак
	 * @param item предмет
	 * @return добавлен ли предмет в рюкзак
	 */
	public boolean addItem(Item item) {
		if (items.size() >= capacity) {
			return false;
		}

		items.add(item);
		return true;
	}

	/**
	 * Функция, возвращающая границу стоимости в поддереве с корнем node
	 * @param node вершина
	 * @return граница
	 */
	private int bound(Node node) {
		if (node.getWeight() >= capacity) return 0;

		int level = node.getHeight() + 1;
		int totalWeight = (int) node.getWeight();
		int totalBound = node.getCost();

		while ((level < items.size()) && (totalWeight + items.get(level).getWeight() <= capacity)) {
			totalWeight += items.get(level).getWeight();
			totalBound += items.get(level).getCost();
			level++;
		}

		if (level < items.size()) {
			totalBound += (capacity - totalWeight) * items.get(level).getCost() / items.get(level).getWeight();
		}

		return totalBound;
	}

	/**
	 * Функция рассчёта максимальной стоимости для текущей вместимости рюкзака
	 * @return максимальную стоимость
	 */
	int getMaxCost() {
		Queue<Node> queue = new LinkedList<>();
		Collections.sort(items);
		int maxCost = 0;

		Node node1 = new Node();
		Node node2 = new Node();
		queue.add(node1);

		while (!queue.isEmpty()) {
			node1 = queue.poll();

			if (node1.getHeight() == -1) node2.setHeight(0);
			if (node1.getHeight() == items.size() - 1) continue;

			node2.setHeight(node1.getHeight() + 1);
			node2.setWeight(node1.getWeight() + items.get(node2.getHeight()).getWeight());
			node2.setCost(node1.getCost() + items.get(node2.getHeight()).getCost());

			if (node2.getWeight() <= capacity && node2.getCost() > maxCost) maxCost = node2.getCost();

			node2.setBound(bound(node2));

			if (node2.getBound() > maxCost) queue.add(node2);

			Node node3 = new Node(node1.getWeight(), node1.getCost(), node2.getHeight());
			node3.setBound(bound(node3));

			if (node3.getBound() > maxCost) queue.add(node3);
		}

		return maxCost;
	}
}
