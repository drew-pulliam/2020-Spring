import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    /*
    Project 4
    Drew Pulliam
    dtp180003
    */

    public static void main(String[] args) throws IOException{
        // ask user for input file names and initialize file input scanners
        Scanner userIn = new Scanner(System.in);
        System.out.println("Please input galaxy filename: ");
        String userInput = userIn.nextLine();

        Scanner galaxyIn = new Scanner(new File(userInput));
        Graph g1 = new Graph();
        while(galaxyIn.hasNextLine()){
            String line = galaxyIn.nextLine();
            String planet = line.split(" ")[0];
            g1.insertVertex(planet);
        }
        galaxyIn = new Scanner(new File(userInput));
        while(galaxyIn.hasNextLine()){
            String line = galaxyIn.nextLine();
            g1.insertEdges(line);
        }

        System.out.println("Please input routes filename: ");
        userInput = userIn.nextLine();
        Scanner routesIn = new Scanner(new File(userInput));
        LinkedList<Patrol> patrols = new LinkedList<Patrol>();
        while(routesIn.hasNextLine()){
            String line = routesIn.nextLine();
            insertSorted(patrols, getPatrol(line, g1));
        }

        PrintWriter out = new PrintWriter(new File("patrols.txt"));
        for(int i = 0; i < patrols.size(); i++){
            out.println(patrols.get(i));
        }

        userIn.close();
        galaxyIn.close();
        routesIn.close();
        out.close();
    }

    private static void insertSorted(LinkedList<Patrol> list, Patrol p){
        if(list.size() == 0){
            list.add(p);
        }else{
            for(int i = 0; i < list.size(); i++){
                if(list.get(i).compareTo(p) > 0){
                    // insert before
                    list.add(i, p);
                    return;
                }
            }
            // add to end of list
            list.add(p);
        }
    }

    private static Patrol getPatrol(String line, Graph g){
        String[] words = line.split(" ");
        String name = words[0];
        int weight = 0;
        for(int i = 1; i < (words.length - 1); i++){
            int curWeight = g.getEdge(words[i], words[i+1]);
            if(curWeight < 0){
                return new Patrol(name,0);
            }else{
                weight += curWeight;
            }
        }
        return new Patrol(name,weight);
    }

    private static class Patrol implements Comparable<Patrol>{
        String name;
        Integer weight;

        Patrol(String n, int w){
            name = n;
            weight = w;
        }

        @Override
        public int compareTo(Patrol otherPatrol) {
            if(weight.compareTo(otherPatrol.weight) != 0){
                return weight.compareTo(otherPatrol.weight);
            }else{
                return name.compareTo(otherPatrol.name);
            }
        }

        @Override
        public String toString(){
            if(weight > 0)
                return name + "\t" + weight + "\tvalid";
            return name + "\t0\tinvalid";
        }

    }
}