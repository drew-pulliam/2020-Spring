import java.io.*;
import java.util.Scanner;

public class Main {
    /*
    Homework 2
    Drew Pulliam
    dtp180003
    */

    public static void main(String[] args) throws IOException{
        Scanner graph1 = new Scanner(new File("graph1.txt"));
        Graph g1 = new Graph();
        System.out.println("graph1:\n"+g1.toString());
        System.out.println("test: empty, not connected graph\n--------------------");
        System.out.println("graph1 isEmpty: "+g1.isEmpty());
        System.out.println("graph1 isConnected: "+g1.isConnected());
        g1.createGraph(graph1);
        System.out.println("\n\ngraph1:\n"+g1.toString());
        System.out.println("test: not empty, connected graph\n--------------------");
        System.out.println("graph1 isEmpty: "+g1.isEmpty());
        System.out.println("graph1 isConnected: "+g1.isConnected());

        Scanner graph2 = new Scanner(new File("graph2.txt"));
        Graph g2 = new Graph();
        g2.createGraph(graph2);
        System.out.println("\n\ngraph2:\n"+g2.toString());
        System.out.println("test: not empty, not connected graph\n--------------------");
        System.out.println("graph2 isEmpty: "+g2.isEmpty());
        System.out.println("graph2 isConnected: "+g2.isConnected());
    }
}