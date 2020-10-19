#include "CarOwner.h"
#include <iostream>
#include <utility>

using namespace std;

CarOwner::CarOwner() = default;

CarOwner::CarOwner(string carNumber,
				   string carBrand,
				   string ownerDescription,
				   bool carStolen) {
	this->carNumber = move(carNumber);
	this->carBrand = move(carBrand);
	this->ownerDescription = move(ownerDescription);
	this->carStolen = carStolen;
}

void CarOwner::show() const {
	string stolen = carStolen ? "stolen" : "not stolen";
	cout
			<< "Car number: " << carNumber
			<< ", car brand: "
			<< carBrand
			<< ", owner description: "
			<< ownerDescription
			<< ", status: "
			<< stolen
			<< endl;
}