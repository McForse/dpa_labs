#include "CarOwnerManager.h"

CarOwnerManager::CarOwnerManager(const string& filename) {
	this->filename = filename;
}

void CarOwnerManager::saveData(vector<CarOwner> &data) {
	ofstream fout(filename);

	for (int i = 0; i < data.size(); i++) {
		fout
				<< data.at(i).carNumber << '|'
				<< data.at(i).carBrand << '|'
				<< data.at(i).ownerDescription << '|'
				<< data.at(i).carStolen;

		if (i != data.size() - 1) {
			fout << endl;
		}
	}

	fout.close();
}

void CarOwnerManager::fillFileTestData() {
	vector<CarOwner> data;
	CarOwner carOwner1 = CarOwner("a000aa777", "BMW", "Describtion", false);
	CarOwner carOwner2 = CarOwner("a111aa777", "Audi", "Describtion", true);
	CarOwner carOwner3 = CarOwner("a222aa777", "Mercedes", "Describtion", true);
	CarOwner carOwner4 = CarOwner("a333aa777", "KIA", "Describtion", false);
	data.push_back(carOwner1);
	data.push_back(carOwner2);
	data.push_back(carOwner3);
	data.push_back(carOwner4);
	saveData(data);
}

void CarOwnerManager::copyDataFromFile(vector<CarOwner> &data) {
	ifstream fin;
	fin.open(filename);

	if (fin.is_open()) {
		data.clear();
		CarOwner p;
		string carNumber;

		while (fin.good()) {
			getline(fin, carNumber, '|');

			if (carNumber.empty() || carNumber == "\n") {
				break;
			} else if (!data.empty()) {
				carNumber.erase(0, 1);
			}

			p.carNumber = carNumber;
			getline(fin, p.carBrand, '|');
			getline(fin, p.ownerDescription, '|');
			fin >> p.carStolen;

			data.push_back(p);
		}
	} else {
		cout << "Could not open the file " << filename << endl;
	}

	fin.close();
}

bool CarOwnerManager::deleteItemInFile(int id) {
	vector<CarOwner> data;
	copyDataFromFile(data);

	if (data.size() < id) {
		return false;
	} else if (id == 1) {
		data.clear();
	} else {
		data[id - 1] = data.at(data.size() - 1);
		data.pop_back();
	}

	saveData(data);
	return true;
}

void CarOwnerManager::printCarOwner(const CarOwner& carOwner, int i) {
	cout << i << ") ";
	carOwner.show();
}

void CarOwnerManager::printCarsStolen() {
	vector<CarOwner> data;
	copyDataFromFile(data);

	if (data.empty()) {
		cout << "File is empty" << endl;
	}

	int count = 0;

	for (auto &i : data) {
		if (i.carStolen) {
			count++;
			printCarOwner(i, count);
		}
	}
}

bool CarOwnerManager::setCarStolen(const string& number, bool carStolen) {
	vector<CarOwner> data;
	copyDataFromFile(data);

	for (auto & i : data) {
		if (i.carNumber == number) {
			i.carStolen = carStolen;
			saveData(data);
			return true;
		}
	}

	return false;
}

void CarOwnerManager::printFileData() {
	vector<CarOwner> data;
	copyDataFromFile(data);

	if (data.empty()) {
		cout << "File is empty" << endl;
	}

	for (int i = 0; i < data.size(); i++) {
		printCarOwner(data.at(i), i + 1);
	}
}
