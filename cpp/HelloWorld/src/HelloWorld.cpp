//============================================================================
// Name        : HelloWorld.cpp
// Author      : Robert 'Bobby' Zenz
// Version     :
// Copyright   : Public Domain
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
using namespace std;

int main() {
	cout << "Hello World!" << endl; // prints Hello World!
	cout << endl;

	for (int counter = 0; counter < 3; counter++) {
		cout << counter << endl;
	}
	cout << endl;

	string newWorld = "newWorld";
	cout << newWorld << endl;

	return 0;
}
