import java.io.*;

public class Main {
    /*
    Homework 1
    Drew Pulliam
    dtp180003
    */

    public static void main(String[] args) throws IOException{
        try {
            System.out.println("\nBin2Dec");
            System.out.print("Decimal \"1111001\" = ");
            System.out.println(bin2Dec("1111001"));
            System.out.print("Decimal \"111sa11\" = ");
            System.out.println(bin2Dec("111sa11"));
        } catch (BinaryFormatException e) {
            System.out.println(e);
        }

        GenericList<Integer> intList = new GenericList<Integer>(new Integer[] {5,3,6,27,0,193});
        System.out.println("\nInteger ArrayList");
        System.out.println("Unsorted:   " + intList.toString());
        intList.insertionSort();
        System.out.println("Sorted:     " + intList.toString());
        System.out.println("Search for 5, index at: " + intList.binarySearch(5));
        System.out.println("Search for 35, index at: " + intList.binarySearch(35));

        GenericList<Double> doubleList = new GenericList<Double>(new Double[] {7.5,7.0,6.4,3.4,3.41});
        System.out.println("\nDouble ArrayList");
        System.out.println("Unsorted:   " + doubleList.toString());
        doubleList.insertionSort();
        System.out.println("Sorted:     " + doubleList.toString());
        System.out.println("Search for 7.0, index at: " + doubleList.binarySearch(7.0));
        System.out.println("Search for 7.3, index at: " + doubleList.binarySearch(7.3));

        GenericList<String> stringList = new GenericList<String>(new String[] {"3","a","2","c","b","1"});
        System.out.println("\nString ArrayList");
        System.out.println("Unsorted:   " + stringList.toString());
        stringList.insertionSort();
        System.out.println("Sorted:     " + stringList.toString());
        System.out.println("Search for \"a\", index at: " + stringList.binarySearch("a"));
        System.out.println("Search for \"q\", index at: " + stringList.binarySearch("q"));
    }
    
    public static int bin2Dec(String binaryNum) throws BinaryFormatException{
        int result = 0;
        for(int i = 0; i < binaryNum.length(); i++){
            if(binaryNum.charAt(i) != '0'){
                if(binaryNum.charAt(i) != '1'){
                    // if any characters in the string are not either 0 or 1, throw exception
                    throw new BinaryFormatException(binaryNum);
                }
            }
        }
        for(int i=binaryNum.length(); i>0; i--){
            // for every character, add
            // character * 2 ^ (character's position)
            result += Integer.parseInt(String.valueOf(binaryNum.charAt(i-1))) * Math.pow(2, (binaryNum.length() - i));
        }
        return result;
    }
}