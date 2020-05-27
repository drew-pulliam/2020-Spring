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
			swap(item, arr[pos]);
			writes++;
		}
		while (pos != start) {
			pos = start;
			for (int i = start + 1; i <n; i++)
				if (arr[i] < item)
					pos += 1;
			while (item == arr[pos])
				pos += 1;
			if (item != arr[pos]){
				swap(item, arr[pos]);
				writes++;
			}
		}
	}
}
	
void swap(int a, int b){
    int temp = a;
    a = b;
    b = a;
    cout << "swapped " << a << " & " << b << endl;
}
	
int main()
{
	int arr[] = {1, 8, 3, 9, 10, 10, 2, 4 };
	int n = sizeof(arr) / sizeof(arr[0]);
	myfunction(arr, n);
	cout << "array : " << endl;
	for (int i = 0; i < n; i++)
		cout << arr[i] << " ";
	return 0;
}
			