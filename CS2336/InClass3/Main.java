import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    /*
    In Class 3
    Drew Pulliam
    dtp180003
    Chris Thornsberry
    CRT180000
    */

    public static void main(String[] args) throws IOException{
            ArrayList<Integer> intList = new ArrayList<Integer>();
            System.out.println("Please input filename: ");
            Scanner userIn = new Scanner(System.in);
            String userInput = userIn.nextLine();
            Scanner fileIn = new Scanner(new File(userInput));
            String[] ints = fileIn.nextLine().split(" ");
            for(int i = 0; i < ints.length; i++){
                try {
                    if(ints[i].matches("[0-9]+")){
                        intList.add(Integer.parseInt(ints[i]));
                    }else{
                        throw new NonInteger(ints[i]);
                    }
                } catch (NonInteger e) {
                    System.out.println(e);
                }
            }
            ArrayList<Integer> uniqueList = removeDuplicates(intList);
            System.out.println(uniqueList);
    }
    
    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list){
        ArrayList<T> finalList = new ArrayList<T>();
        finalList.add(list.get(0));
        for(int i = 1; i < list.size(); i++){
            if(!finalList.contains(list.get(i))){
                finalList.add(list.get(i));
            }
        }
        return finalList;
    }
}