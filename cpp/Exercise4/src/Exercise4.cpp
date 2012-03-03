//============================================================================
// Name        : Exercise1.cpp
// Author      : Robert 'Bobby' Zenz
// Version     :
// Copyright   : Public Domain
// Description : http://en.wikibooks.org/wiki/C%2B%2B_Programming/Exercises/Variables_and_types
//               Write a program that asks the user to type the price without tax
//               of one kilogram of tomatoes,the number of kilograms you want
//               to buy and the tax in percent units. The program must write
//               the total price including taxes.
//============================================================================

#include <iostream>
using namespace std;

int main() {
	double pricePerKg;
	double amount;
	double tax;

	cout << "Price per kg: ";
	cin >> pricePerKg;

	cout << "Amount: ";
	cin >> amount;

	cout << "Tax: ";
	cin >> tax;

	cout << (amount * pricePerKg) * (tax / 100 + 1) << endl;

	return 0;
}
