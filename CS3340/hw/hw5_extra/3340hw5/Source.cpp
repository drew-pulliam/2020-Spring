#include <iostream>
#include <iomanip>
using namespace std;

int main() {
	int days;
	float total = 0.0;

	cout << "For how many days do you have sales amounts? ";
	cin >> days;

	for (int count = 1; count <= days; count++) {
		double sales;
		cout << "Enter the sales for the day " << count << ": ";
		cin >> sales;
		total += sales;
	}

	cout << fixed << showpoint << setprecision(2);
	cout << "The total sales are $" << total << endl;

	return 0;
}