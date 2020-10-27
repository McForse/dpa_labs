#include "Graphs.h"

Graph::Graph() {
	reset();
}

Graph::Graph(int vertex) {
	reset();
	setVertices(vertex);
}

Graph::~Graph() {
	deleteArrays();
}

void Graph::isOriented(bool status) {
	m_isOriented = status;
}

void Graph::setVertices(int vertices) {
	if (vertices < 1) {
		sendError("Количество вершин не может быть отрицательным или нулевым");
		return;
	}

	if (m_vertices != 0) deleteArrays();

	m_vertices = vertices;
	setupArrays();
}

void Graph::setupArrays() {
	lists.resize(m_vertices);
	length = new int[m_vertices];

	for (int i = 0; i < m_vertices; i++)
		length[i] = -1;
}

void Graph::deleteArrays() {
	for (int i = 0; i < m_vertices; i++)
		lists[i].clear();

	delete length;
}

void Graph::reset() {
	m_vertices = 0;
	m_roads = 0;
	m_isOriented = true;
	m_startVertex = 0;
}

void Graph::setRoadBetweenVertex(int from, int to, int size) {
	if (from < 1 || from > m_vertices || to < 1 || to > m_vertices) {
		sendError("Номер вершины не может быть больше общего числа вершин в графе, быть отрицательным или нулевым");
		return;
	} else if (from == to) {
		sendError("Нельзя назначать дорогу одной и той же вершине");
		return;
	} else if (size <= 0) {
		sendError("Длина дороги не может быть отрицательным числом или нулем");
		return;
	}

	Vertex vert{};
	vert.to = to;
	vert.size = size;

	lists[from - 1].push_back(vert);
	m_roads++;
}

void Graph::removeRoadBetweenVertex(int from, int to, int size) {
	if (from < 1 || from > m_vertices || to < 1 || to > m_vertices) {
		sendError("Номер вершины не может быть больше общего числа вершин в графе, быть отрицательным или нулевым");
		return;
	} else if (size <= 0) {
		sendError("Длина дороги не может быть отрицательным числом или нулем");
		return;
	}

	auto it = lists[from - 1].begin();

	while (it != lists[from - 1].end()) {
		if (it->to == to && it->size == size) {
			lists[from - 1].erase(it);
			m_roads--;
			break;
		}

		it++;
	}
}

/**
 * Поиск кратчайшего пути из одной вершины графа в другую
 * @param from - начальная вершина
 * @param to - конечная вершина
 * @return 0 - начальная и конечная вершина совпадает
 * @return -1 - отсутствие пути (преимущественно для ориентированного графа)
 * @return -2 - не задано количество вершин или ни одной дороги
 * @return -3 - заданны неверные параметры вершин
 * @return число > 0 - длина кратчайшего пути
 */
int Graph::findShortestWay(int from, int to) {
	if (from < 1 || from > m_vertices || to < 1 || to > m_vertices) return -3;

	if (m_vertices == 0 || m_roads == 0)
		return -2;

	m_startVertex = from;

	if (from != to) {
		findRoads(m_startVertex);

		return length[to - 1];
	} else return 0;
}

void Graph::findRoads(int from) {
	for (auto & i : lists[from - 1]) {
		process(from, i.to, i.size);
	}

	if (!m_isOriented) {
		for (int i = 0; i < m_vertices; i++) {
			if (i == from - 1) continue;

			for (auto & j : lists[i])
				if (j.to == from)
					process(from, i + 1, j.size);
		}
	}
}

void Graph::process(int from, int to, int size) {
	if (to != m_startVertex) {
		int sum = (length[from - 1] != -1) ? length[from - 1] + size : size;

		if (sum < length[to - 1] || length[to - 1] == -1) {
			length[to - 1] = sum;
			findRoads(to);
		}
	}
}

void Graph::dumpData() {
	cout << endl << setw(11) << "Список дорог:" << endl;

	for (int i = 0; i < m_vertices; i++) {
		for (auto & j : lists[i])
			cout << "Путь: " << i + 1 << " -> " << j.to
				<< " = " << j.size << endl;
	}

	int tableLength = m_vertices * 5 + 1;
	cout << endl << setw(tableLength / 2 + 6) << "Кратчайшие пути для каждой вершины:" << endl;

	for (int i = 0; i < tableLength; i++)
		cout << "-";

	cout << endl << "|";

	for (int i = 0; i < m_vertices; i++)
		cout << setw(3) << length[i] << setw(2) << "|";

	cout << endl;

	for (int i = 0; i < tableLength; i++)
		cout << "-";
	
	cout << endl << endl;
}

void Graph::sendError(const char* error, const char* what) {
	cout << "Ошибка: " << error << "! " << what << endl;
}