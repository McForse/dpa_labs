package ru.mirea.n01pr9;

import ru.mirea.n01pr9.queue.PriorityQueue;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
		Queueable<Request> queue = new PriorityQueue<>(new RequestComparator());
		queue.add(new Request(EmployeeCategory.K, 1, 5));
		queue.add(new Request(EmployeeCategory.K, 2, 10));
		queue.add(new Request(EmployeeCategory.M, 3, 5));
		queue.add(new Request(EmployeeCategory.P, 4, 5));
		queue.add(new Request(EmployeeCategory.P, 4, 15));
		queue.add(new Request(EmployeeCategory.M, 4, 15));
		printData(queue);
    }

    public static void printData(Queueable<Request> queue) {
		Map<EmployeeCategory, Integer> timeMap = new HashMap<>();

		System.out.println("Requests:\n");
		while (!queue.isEmpty()) {
			Request request = queue.peek();
			System.out.println(request);
			timeMap.put(request.getCategory(),
					timeMap.getOrDefault(request.getCategory(), 0)
							+ request.getTimeMin());
			queue.remove();
		}

		System.out.println("\nTime needs:");
		for (Map.Entry<EmployeeCategory, Integer> entry : timeMap.entrySet()) {
			System.out.format(
					"\n%s - %d minutes",
					entry.getKey().getName(),
					entry.getValue()
			);
		}
	}
}
