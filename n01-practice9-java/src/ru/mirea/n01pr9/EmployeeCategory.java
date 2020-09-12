package ru.mirea.n01pr9;

public enum EmployeeCategory {
	M("Manager", 1),
	K("Controller", 2),
	P("Worker", 3);

	private final String name;
	private final int priority;

	EmployeeCategory(String name, int priority) {
		this.name = name;
		this.priority = priority;
	}

	public String getName() {
		return name;
	}

	public int getPriority() {
		return priority;
	}
}
