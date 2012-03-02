//============================================================================
// Name        : Exercise1.cpp
// Author      : Robert 'Bobby' Zenz
// Version     :
// Copyright   : Public Domain
// Description : http://en.wikibooks.org/wiki/C%2B%2B_Programming/Exercises/Variables_and_types
//               Write a program that asks the user to type 5 integers and
//               writes the average of the 5 integers. This program can use
//               only 2 variables.
//============================================================================

#include <iostream>
using namespace std;

int main() {
	float sum = 0;

	for (int counter = 0; counter < 5; counter++) {
		int input;
		cout << "Gimme: ";
		cin >> input;

		sum += input;
	}

	cout << "Average: " << (sum / 5) << endl;
	return 0;
}
