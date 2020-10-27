#ifndef GRAPHS_H
#define GRAPHS_H

#include <iostream>
#include <iomanip>
#include <vector>
#include <list>

using namespace std;

/**
 * Нахождения кратчайших путей в графе методом Дейкстра
 */
class Graph {
public:
	// Конструктор без параметров
	Graph();
	// Конструктор с установкой количества вершин графа
	Graph(int vertex);
	~Graph();

	// Установка типа графа (true - ориентированный, false - неориентированный)
	void isOriented(bool status);

	// Установка количеста вершин графа
	void setVertices(int vertex);

	// Очиститка всех ранее введенных данных
	void reset();

	// Установка длины между вершинами графа
	void setRoadBetweenVertex(int from, int to, int size);

	// Удалить дорогу между вершинами графа
	void removeRoadBetweenVertex(int from, int to, int size);

	// Поиск кратчайшего пути из одной вершины графа в другую
	int findShortestWay(int from, int to);

	// Вывод данных списков и массивов
	void dumpData();

private:
	// Структура вершины
	struct Vertex {
		int to;
		int size;
	};

	int m_vertices;
	int m_roads;
	bool m_isOriented;

	vector<list<Vertex>> lists;
	int *length;

	// Подготовка и сброс массивов
	void setupArrays();

	// Удаление списков и массивов (освобождение памяти)
	void deleteArrays();

	int m_startVertex;

	// Функции поиска кратчайших путей
	// Нахождение всех дорог из вершины графа
	void findRoads(int from);

	// Определение кратчайшего пути между двумя вершинами
	void process(int from, int to, int size);

	// Вывод ошибок
	static void sendError(const char* error, const char* what = "");
};

#endif //GRAPHS_H