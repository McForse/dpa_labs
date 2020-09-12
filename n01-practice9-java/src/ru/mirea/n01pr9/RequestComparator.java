package ru.mirea.n01pr9;

import java.util.Comparator;

public class RequestComparator implements Comparator<Request> {
	@Override
	public int compare(Request r1, Request r2) {
		return Integer.compare(r2.getPriority(), r1.getPriority());
	}
}
