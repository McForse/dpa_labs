#include <iostream>
#include "CarOwnerManager.h"

int main() {
	string filename = "data.txt";
	CarOwnerManager manager(filename);
	manager.fillFileTestData();

	cout << "File content:" << endl;
	manager.printFileData();

	cout << endl << "After delete 2 car owner:" << endl;
	manager.deleteItemInFile(2);
	manager.printFileData();

	cout << endl << "After set stolen status to a000aa777:" << endl;
	manager.setCarStolen("a000aa777", true);
	manager.printFileData();

	cout << endl << "All stolen cars:" << endl;
	manager.printCarsStolen();
	return 0;
}
