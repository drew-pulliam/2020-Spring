import java.util.ArrayList;

public class GenericList<T extends Comparable> {
    /*
    Homework 1
    Drew Pulliam
    dtp180003
    */

    ArrayList<T> list;

    public GenericList(T x[]){
        list = new ArrayList<T>();
        for(int i = 0; i < x.length; i++) {
            list.add(x[i]);
        }
    }

    public void insertionSort(){
        for (int i = 1; i < list.size(); i++){
            int j = i - 1;
            T test = list.get(i);
            while(j >= 0 && list.get(j).compareTo(test) > 0){
                // continue shifting elements until test element is in right place
                list.set(j+1, list.get(j));
                j--;
            }
            list.set(j+1, test);
        }
    }

    public int binarySearch(T item){
        // convience function
        // user does not have to call recursive function with left and right bounds
        return binarySearch(item, 0, (list.size() - 1));
    }

    private int binarySearch(T item, int l, int r){
        if(r >= l){
            int middle = l + (r - l) / 2;
            if(list.get(middle).compareTo(item) == 0){
                // element is in the middle
                return middle;
            }
            else if(list.get(middle).compareTo(item) > 0){
                // element is in the "left" side
                return binarySearch(item, l, middle - 1);
            }
            else{
                // element is in the "right" side
                return binarySearch(item, middle + 1, r);
            }
        }
        // element is not in the ArrayList at all
        return -1;
    }

    @Override
    public String toString(){
        String result = "";
        for(int i = 0; i < list.size() - 1; i++){
            result += list.get(i) + ",";
        }
        result += list.get(list.size() - 1);
        return result;
    }
    
}