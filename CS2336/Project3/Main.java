import java.util.Scanner;

import java.io.*;

public class Main {
    /*
    Project 3
    Drew Pulliam
    dtp180003
    */

    public static void main(String[] args) throws IOException{

        // initialize user input scanner and file outputs
        // PrintWriter areasOut = new PrintWriter(new File("pilot_areas.txt"));
        PrintWriter datOut = new PrintWriter(new File("cidercade1.dat"));
        // Scanner userIn = new Scanner(System.in);

        // ask user for input file names and initialize file input scanners
        // System.out.println("Please input routes filename: ");
        // String userInput = userIn.nextLine();
        // Scanner routesIn = new Scanner(new File(userInput));

        // System.out.println("Please input commands filename: ");
        // userInput = userIn.nextLine();
        // Scanner commandsIn = new Scanner(new File(userInput));
        Payload payload = new Payload("Pacman", 9001, "cov", 300);
        Node<Payload> node = new Node<Payload>(payload);
        BinTree<Payload> bt = new BinTree<Payload>(node);

        payload = new Payload("Mrs. Pacman", 1337, "jst", 254);
        node = new Node<Payload>(payload);
        bt.add(node);

        payload = new Payload("Frogger", 4556, "gta", 148);
        node = new Node<Payload>(payload);
        bt.add(node);

        payload = new Payload("Galaga", 1114, "frc", 118);
        node = new Node<Payload>(payload);
        bt.add(node);

        datOut.println(bt.databaseOutput());

        // userIn.close();
        // routesIn.close();
        // commandsIn.close();
        datOut.close();
        // areasOut.close();
    }
    
}