import java.util.ArrayList;
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
        PrintWriter datOut = new PrintWriter(new File("cidercade.dat"));
        PrintWriter logOut = new PrintWriter(new File("cidercade.log"));
        Scanner userIn = new Scanner(System.in);

        // ask user for input file names and initialize file input scanners
        System.out.println("Please input database filename: ");
        String userInput = userIn.nextLine();
        Scanner dataIn = new Scanner(new File(userInput));

        System.out.println("Please input commands filename: ");
        userInput = userIn.nextLine();
        Scanner commandsIn = new Scanner(new File(userInput));
        
        BinTree<Payload> bt = new BinTree<Payload>();

        while(dataIn.hasNextLine()){
            String line = dataIn.nextLine();
            String[] data = line.split(", ");
            String name = data[0];
            int highScore = Integer.parseInt(data[1]);
            String initials = data[2];
            int plays = Integer.parseInt(data[3]);
            Payload payload = new Payload(name, highScore, initials, plays);
            Node<Payload> node = new Node<Payload>(payload);
            bt.add(node);
        }

        while(commandsIn.hasNextLine()){
            String command = commandsIn.nextLine();
            doCommand(command, bt, logOut);
        }
        datOut.println(bt.databaseOutput());

        userIn.close();
        dataIn.close();
        commandsIn.close();
        datOut.close();
        logOut.close();
    }

    private static void doCommand(String line, BinTree<Payload> bt, PrintWriter out){
        int commandNum = Integer.parseInt(line.substring(0,1));
        Payload payload;
        Node<Payload> node;
        String[] temp;
        String name;
        String[] vars;
        switch(commandNum){
            case 1:
                // add record
                temp = line.split("\"");
                name = temp[1];
                vars = (temp[2].trim()).split(" ");
                int highScore = Integer.parseInt(vars[0]);
                String initials = vars[1];
                int plays = Integer.parseInt(vars[2]);
                payload = new Payload(name, highScore, initials, plays);
                node = new Node<Payload>(payload);
                bt.add(node);
                out.println("RECORD ADDED");
                out.println("Name: " + name);
                out.println(node.getObject().basicLogOutput());
                break;
            case 2:
                // search for record
                String searchTerm = line.substring(2,line.length());
                payload = new Payload(searchTerm);
                node = new Node<Payload>(payload);
                ArrayList<Node<Payload>> matches = bt.search(node);
                if(matches == null){
                    out.println(searchTerm + " NOT FOUND\n");
                }else if(matches.size() == 0){
                    out.println(searchTerm + " NOT FOUND\n");
                }else{
                    // print out all matches
                    for(Node<Payload> searchedNode : matches){
                        out.println(searchedNode.getObject().getName() + " FOUND");
                        out.println(searchedNode.getObject().basicLogOutput());
                    }   
                }
                break;
            case 3:
                // edit record
                temp = line.split("\"");
                name = temp[1];
                vars = (temp[2].trim()).split(" ");
                int field = Integer.parseInt(vars[0]);
                String val = vars[1];
                edit(name, field, val, bt, out);
                break;
            case 4:
                // delete record
                String deleteName = line.substring(2,line.length());
                payload = new Payload(deleteName);
                node = new Node<Payload>(payload);
                Node<Payload> tempNode = bt.delete(node);
                out.println("RECORD DELETED");
                out.println("Name: " + deleteName);
                out.println(tempNode.getObject().basicLogOutput());
                break;
            case 5:
                // sort records
                String order = line.split(" ")[1];
                if(order.equals("asc")){
                    out.println("RECORDS SORTED ASCENDING");
                }else{
                    out.println("RECORDS SORTED DESCENDING");
                }
                out.println(bt.sort(order));
                break;
            default:
                break;
        }
    }

    private static void edit(String name, int field, String val, BinTree<Payload> bt, PrintWriter out){
        Payload payload = new Payload(name, 0, "", 0);
        Node<Payload> node = new Node<Payload>(payload);
        ArrayList<Node<Payload>> matches = bt.search(node);
        Node<Payload> toEdit = matches.get(0);
        // System.out.println(toEdit);
        String fieldName = "";
        if(field == 1){
            toEdit.getObject().setHighScore(Integer.parseInt(val));
            fieldName = "high score";
        }else if(field == 2){
            toEdit.getObject().setInitials(val);
            fieldName = "initials";
        }else if(field == 3){
            toEdit.getObject().setPlays(Integer.parseInt(val));
            fieldName = "plays";
        }
        out.println(name + " UPDATED");
        out.println("UPDATE TO " + fieldName + " - VALUE " + val);
        out.println(toEdit.getObject().basicLogOutput());
        // System.out.println(toEdit);
    }
    
}