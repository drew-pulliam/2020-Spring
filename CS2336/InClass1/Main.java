import java.util.Scanner;
import java.io.*;

public class Main {
    /*
    In Class Assignment 1
    Drew Pulliam
    dtp180003
    Aras Sahebi
    aps150530
    */

    public static void main(String[] args) throws IOException{
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please input equation: ");
        // format: ax^2+bx+c=0
        // no spaces
        // a, b, and c can be negative
        // if negative, no + sign, just - before the coefficient

        String userInput = userIn.nextLine();

        int x = userInput.indexOf("x");
        int a = Integer.parseInt(userInput.substring(0, x));
        int carrot2 = userInput.indexOf("^2");
        x = userInput.indexOf("x", x+1);
        int b = Integer.parseInt(userInput.substring(carrot2+2, x));
        int equals = userInput.indexOf("=");
        int c = Integer.parseInt(userInput.substring(x+1, equals));

        //2x^2+9x+2=0

        // String[] eqn = userInput.split("=");
        // String[] variables = eqn[0].split("x");
        // int a = Character.getNumericValue(variables[0].charAt(variables[0].length()-1));
        // int b = Character.getNumericValue(variables[1].charAt(variables[1].length()-1));
        // int c = Character.getNumericValue(variables[2].charAt(variables[2].length()-1));

        QuadraticEquation equation = new QuadraticEquation(a,b,c);

        double root1 = equation.getRoot1();
        double root2 = equation.getRoot2();

        System.out.println("Root1: "+root1);
        System.out.println("Root2: "+root2);
        userIn.close();
    }
    
}