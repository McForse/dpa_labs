package ru.mirea.n08pr16;

/**
 * Модель вершины для метода ветвей и границ
 */
public class Node {
	private double weight;
	private int cost;
	private int height;
	private int bound;

	public Node() {
		this.height = -1;
	}

	public Node(double weight, int cost, int height) {
		this.weight = weight;
		this.cost = cost;
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getBound() {
		return bound;
	}

	public void setBound(int bound) {
		this.bound = bound;
	}
}
