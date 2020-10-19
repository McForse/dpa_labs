#ifndef CAROWNERMANAGER_H
#define CAROWNERMANAGER_H
#include <iostream>
#include <fstream>
#include <vector>
#include "CarOwner.h"

using namespace std;

class CarOwnerManager {
public:
	explicit CarOwnerManager(const string& filename);

	bool deleteItemInFile(int id);

	void printCarsStolen();

	bool setCarStolen(const string &number, bool carStolen);

	void printFileData();

	void fillFileTestData();

private:
	string filename;

	void saveData(vector<CarOwner> &data);

	void copyDataFromFile(vector<CarOwner> &data);

	static void printCarOwner(const CarOwner &carOwner, int i);
};


#endif //CAROWNERMANAGER_H
