//============================================================================
// Name        : Exercise1.cpp
// Author      : Robert 'Bobby' Zenz
// Version     :
// Copyright   : Public Domain
// Description : http://en.wikibooks.org/wiki/C%2B%2B_Programming/Exercises/Variables_and_types
//               Write a program that asks the user to type the width and
//               the length of a rectangle and then outputs to the screen
//               the area and the perimeter of that rectangle.
//============================================================================

#include <iostream>
using namespace std;

int main() {
	int width;
	int height;

	cout << "Please enter width: ";
	cin >> width;

	cout << "Please enter height: ";
	cin >> height;

	cout << "Area: " << (width * height) << endl;
	cout << "Perimeter: " << (width * 2 + height * 2) << endl;

	return 0;
}
