#include <iostream>
using namespace std;
void myfunction(int arr[], int n)
{
	int writes = 0;
	for (int start = 0; start <= n - 2; start++) {
		int item = arr[start];
		int pos = start;
		for (int i = start + 1; i < n; i++)
			if (arr[i] < item)
				pos++;
		if (pos == start)
			continue;
		while (item == arr[pos])
			pos += 1;
		if (pos != start) {
			cout << arr[0] << " " << arr[1] << " " << arr[2] << " " << arr[3] << " " << arr[4] << " " << arr[5] << " " << arr[6] << " " << arr[7] << " " << "\t";
			cout << "1 start: " << start << " item: " << item << " & arr[pos]: " << arr[pos] << " arr[start]: " << arr[start] << "\t\t";
			swap(item, arr[pos]);
			cout << "1 start: " << start << " item: " << item << " & arr[pos]: " << arr[pos] << " arr[start]: " << arr[start] << "\t\t";
			cout << arr[0] << " " << arr[1] << " " << arr[2] << " " << arr[3] << " " << arr[4] << " " << arr[5] << " " << arr[6] << " " << arr[7] << " " << endl;
			writes++;
		}
		while (pos != start) {
			pos = start;
			for (int i = start + 1; i < n; i++)
				if (arr[i] < item)
					pos += 1;
			while (item == arr[pos])
				pos += 1;
			if (item != arr[pos]) {
				cout << arr[0] << " " << arr[1] << " " << arr[2] << " " << arr[3] << " " << arr[4] << " " << arr[5] << " " << arr[6] << " " << arr[7] << " " << "\t";
				cout << "2 start: " << start << " item: " << item << " & arr[pos]: " << arr[pos] << " arr[start]: " << arr[start] << "\t\t";
				swap(item, arr[pos]);
				cout << "2 start: " << start << " item: " << item << " & arr[pos]: " << arr[pos] << " arr[start]: " << arr[start] << "\t\t";
				cout << arr[0] << " " << arr[1] << " " << arr[2] << " " << arr[3] << " " << arr[4] << " " << arr[5] << " " << arr[6] << " " << arr[7] << " " << endl;
				writes++;
			}
		}
	}
}

/*void swap(int a, int b) {
	cout << "swapped " << a << " & " << b << endl;
	int temp = a;
	a = b;
	b = a;
}*/

int main()
{
	int arr[] = { 1, 8, 3, 9, 10, 10, 2, 4 };
	int n = sizeof(arr) / sizeof(arr[0]);
	myfunction(arr, n);
	cout << "array : " << endl;
	for (int i = 0; i < n; i++)
		cout << arr[i] << " ";
	return 0;
}
