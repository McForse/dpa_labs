#include "Graphs.h"

using namespace std;

void manual() {
	Graph myGraph;
	int vertices, roads, from, to, vertex1, vertex2, size;
	bool isOriented;

	cout << "Введите число вершин и количество дорог:" << endl;
	cin >> vertices >> roads;
	myGraph.setVertices(vertices);

	cout << "Установите тип графа (1 - ориентированный; 0 - неориентированный):" << endl;
	cin >> isOriented;
	myGraph.isOriented(isOriented);

	cout << "Установка дорог между вершинами (from to size):" << endl;
	for (int i = 0; i < roads; i++) {
		cout << i + 1 << ") ";
		cin >> vertex1 >> vertex2 >> size;

		myGraph.setRoadBetweenVertex(vertex1, vertex2, size);
	}

	cout << "От какой и до какой вершины найти кратчайший путь? (from to):" << endl;
	cin >> from >> to;
	cout << "Кратчайший путь - " << myGraph.findShortestWay(from, to) << endl;
}

// Индивидуальное задание 14.4.6
void example() {
	Graph myGraph(8);
	myGraph.isOriented(false);
	myGraph.setRoadBetweenVertex(1, 2, 23);
	myGraph.setRoadBetweenVertex(1, 3, 12);
	myGraph.setRoadBetweenVertex(2, 3, 25);
	myGraph.setRoadBetweenVertex(2, 5, 22);
	myGraph.setRoadBetweenVertex(2, 8, 35);
	myGraph.setRoadBetweenVertex(3, 4, 18);
	myGraph.setRoadBetweenVertex(4, 6, 20);
	myGraph.setRoadBetweenVertex(5, 6, 23);
	myGraph.setRoadBetweenVertex(5, 7, 14);
	myGraph.setRoadBetweenVertex(6, 7, 24);
	myGraph.setRoadBetweenVertex(7, 8, 16);
	cout << "Кратчайший путь между вершинами 1 и 8: "
		<< myGraph.findShortestWay(1, 8) << endl;
	myGraph.dumpData();
}

int main() {
	example();
	//manual();
	return 0;
}
