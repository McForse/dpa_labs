package ru.mirea.n01pr9;

public class Request {
	private EmployeeCategory category;
	private int id;
	private int timeMin;

	public Request(EmployeeCategory category, int id, int timeMin) {
		this.category = category;
		this.id = id;
		this.timeMin = timeMin;
	}

	public EmployeeCategory getCategory() {
		return category;
	}

	public void setCategory(EmployeeCategory category) {
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTimeMin() {
		return timeMin;
	}

	public void setTimeMin(int timeMin) {
		this.timeMin = timeMin;
	}

	public int getPriority() {
		return category.getPriority();
	}

	@Override
	public String toString() {
		return "Request {" +
				"category = " + category +
				", id = " + id +
				", timeMin = " + timeMin +
				'}';
	}
}
