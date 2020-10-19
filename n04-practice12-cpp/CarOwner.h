#ifndef CAROWNER_H
#define CAROWNER_H

#include <string>

using namespace std;

class CarOwner {
public:
	CarOwner();

	CarOwner(string carNumber,
			 string carBrand,
			 string ownerDescription,
			 bool isCarStolen
	);

	string carNumber;
	string carBrand;
	string ownerDescription;
	bool carStolen{};

	void show() const;
};


#endif //CAROWNER_H
