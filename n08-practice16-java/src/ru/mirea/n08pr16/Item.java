package ru.mirea.n08pr16;

/**
 * Модель предмета
 */
class Item implements Comparable<Item> {
	private final double weight;
	private final int cost;

	/**
	 * Конструктор класса
	 * @param weight масса предмета
	 * @param cost стоимость предмета
	 */
	public Item(double weight, int cost) {
		this.weight = weight;
		this.cost = cost;
	}

	/**
	 * Функция, возвращающая массу предмета
	 * @return масса предмета
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Функция, возвращающая стоимость предмета
	 * @return стоимость предмета
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * Сравнение предметов по их стоимости на единицу веса
	 * @param item2 второй элемент
	 * @return 1 если стоимоть на единицу веса текущего предмета больше второго,
	 *         0 если стоимоть на единицу веса предметов одинакова,
	 *         -1 если стоимоть на единицу веса текущего предмета меньше второго
	 */
	@Override
	public int compareTo(Item item2) {
		return (int) (item2.cost / item2.weight - cost / weight);
	}

	@Override
	public String toString() {
		return "Предмет {" +
				"вес=" + weight +
				", стоимость=" + cost +
				'}';
	}
}