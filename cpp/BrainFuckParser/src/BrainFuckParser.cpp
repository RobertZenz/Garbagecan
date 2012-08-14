//============================================================================
// Name        : BrainFuckParser.cpp
// Author      : Robert 'Bobby' Zenz
// Version     :
// Copyright   : Public Domain
// Description : BrainFuckParser
//============================================================================

#include <iostream>
#include <fstream>

using namespace std;

#define INC_POINTER '>'
#define DEC_POINTER '<'
#define INC_VALUE '+'
#define DEC_VALUE '-'
#define OUTPUT_ASCII '.'
#define INPUT_ASCII ','
#define IF_ZERO '['
#define IF_NOT_ZERO ']'

int main(int argc, char* argv[]) {
	if (argc <= 1) {
		cout << "Input-File required..." << endl;
		return 0;
	}

	ifstream input;
	input.open(argv[1], ios::in | ios::binary | ios::ate);

	ifstream::pos_type programLength = input.tellg();
	char* program = new char[programLength];

	input.seekg(0, ios::beg);
	input.read(program, programLength);

	input.close();

	char* data = new char[30000];
	fill_n(data, 30000, 0);

	for (int idx = 0; idx < programLength; idx++) {
		if (program[idx] == INC_POINTER) {
			++data;
		} else if (program[idx] == DEC_POINTER) {
			--data;
		} else if (program[idx] == INC_VALUE) {
			++*data;
		} else if (program[idx] == DEC_VALUE) {
			--*data;
		} else if (program[idx] == OUTPUT_ASCII) {
			cout << *data;
		} else if (program[idx] == INPUT_ASCII) {
			cin.read(data, 1);
		} else if (program[idx] == IF_ZERO) {
			if(*data == 0) {
				while(program[idx] != IF_NOT_ZERO) {
					idx++;
				}
			}
		} else if (program[idx] == IF_NOT_ZERO) {
			if(*data != 0) {
				do {
					idx--;
				} while(program[idx] != IF_ZERO);
			}
		}
	}

	return 0;
}
