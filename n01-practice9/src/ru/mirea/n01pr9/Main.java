package ru.mirea.n01pr9;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
		PriorityQueue<Request> queue = new PriorityQueue<>(new RequestComparator());
		queue.add(new Request(EmployeeCategory.K, 1, 5));
		queue.add(new Request(EmployeeCategory.K, 2, 10));
		queue.add(new Request(EmployeeCategory.M, 3, 5));
		queue.add(new Request(EmployeeCategory.P, 4, 5));
		queue.add(new Request(EmployeeCategory.P, 4, 15));

		Map<EmployeeCategory, Integer> timeMap = new HashMap<>();


		while (!queue.isEmpty()) {
			Request request = queue.peek();
			System.out.println(request);
			timeMap.put(request.getCategory(),
					timeMap.getOrDefault(request.getCategory(), 0)
							+ request.getTimeMin());
			queue.remove();
		}

		for (Map.Entry<EmployeeCategory, Integer> entry : timeMap.entrySet()) {
			System.out.format(
					"\n%s - %d minutes",
					entry.getKey().getName(),
					entry.getValue()
			);
		}
    }
}
