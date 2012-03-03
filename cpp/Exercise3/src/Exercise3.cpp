//============================================================================
// Name        : Exercise1.cpp
// Author      : Robert 'Bobby' Zenz
// Version     :
// Copyright   : Public Domain
// Description : http://en.wikibooks.org/wiki/C%2B%2B_Programming/Exercises/Variables_and_types
//               Write a program that asks the user to type 2 integers A and B and
//               exchange the value of A and B.
//============================================================================

#include <iostream>
using namespace std;

int main() {
	int a;
	int b;

	cout << "Enter first: ";
	cin >> a;

	cout << "Enter second: ";
	cin >> b;

	int swapper;
	swapper = a;
	a = b;
	b = swapper;

	cout << "A: " << a << endl;
	cout << "B: " << b << endl;

	return 0;
}
