//============================================================================
// Name        : Exercise5.cpp
// Author      : Robert 'Bobby' Zenz
// Version     :
// Copyright   : Public Domain
// Description : http://en.wikibooks.org/wiki/C%2B%2B_Programming/Exercises/Variables_and_types
//               Find the Answer to 100C10 ; Find the Answer to 20C5  ; Find the Answer to 40C30 ; Find the Answer to 90C10.6 ; Find the Answer to 120C30 ; Find the Answer to 150C32.32 ;

Write a program in C++ ISO/IEC or C++/CLI without ever closing down the program find the answers to all above questions.
//============================================================================

#include <iostream>
#include <cmath>
using namespace std;

struct Point {
	int x;
	int y;
};

void getPoint(string pointName, Point& point) {
	cout << endl << "Point " << pointName << "..." << endl;

	cout << "X: ";
	cin >> point.x;

	cout << "Y: ";
	cin >> point.y;
}

int main() {
	Point pointA;
	Point pointB;

	getPoint("A", pointA);
	getPoint("B", pointB);

	double distanceX = abs(pointA.x - pointB.x);
	double distanceY = abs(pointA.y - pointB.y);
	double distance = sqrt(pow(distanceX, 2) + pow(distanceY, 2));

	cout << endl << "Distance: " << distance;

	return 0;
}
