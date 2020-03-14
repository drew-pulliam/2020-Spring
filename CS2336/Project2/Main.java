import java.util.Scanner;

import java.io.*;

public class Main {
    /*
    Project 2
    Drew Pulliam
    dtp180003
    */

    // declare variables
    private static String[] pilotList = new String[20];
    private static double[][][] masterList = new double[20][20][2];
    private static int[] lastElem = new int[20];
    private static int pilotNum = 0;

    public static void main(String[] args) throws IOException{

        // initialize user input scanner and file outputs
        PrintWriter areasOut = new PrintWriter(new File("pilot_areas.txt"));
        PrintWriter resultsOut = new PrintWriter(new File("results.txt"));
        Scanner userIn = new Scanner(System.in);

        // ask user for input file names and initialize file input scanners
        System.out.println("Please input routes filename: ");
        String userInput = userIn.nextLine(); // TODO
        // String userInput = "pilot_routes1.txt";
        Scanner routesIn = new Scanner(new File(userInput));

        System.out.println("Please input commands filename: ");
        userInput = userIn.nextLine(); // TODO
        // userInput = "sample_commands.txt";
        Scanner commandsIn = new Scanner(new File(userInput));

        // loop through the entire input file, saving pilot names and coordinates
        while(routesIn.hasNextLine()){
            // save pilot name for output later
            // String pilotName = routesIn.next();
            // pilotList[pilotNum] = pilotName;
            
            // get coordinates
            parseCoords(routesIn, areasOut); // TODO
            
            // // increment pilot number
            // pilotNum++;
        }

        LinkedList<Payload> l = new LinkedList<Payload>();

        for(int i = 0; i < pilotNum; i++){
            double area = calculateArea(i);
            Payload p = new Payload(pilotList[i], area);
            Node<Payload> n = new Node<Payload>(p);
            l.add(n);
        }

        while(commandsIn.hasNextLine()){
            String command = commandsIn.nextLine();
            String[] words = command.split(" ");
            if(words.length == 1){
                // command is a search
                double areaToSearch = -1;
                try{
                    // is a double, search by area
                    areaToSearch = Double.parseDouble(command);
                    resultsOut.println(search(l, areaToSearch));
                }catch(Exception e){
                    System.out.println(e);
                }
                if(areaToSearch < 0){
                    // not a double, search by name
                    if(isValidName(command)){
                        resultsOut.println(search(l, command));
                    }
                }
            }else{
                if(!words[0].equals("sort")){
                    // command might be a multi word name search
                    if(isValidName(command)){
                        // command is a search
                        resultsOut.println(search(l, command));
                    }
                    continue;
                }
                // command is a sort
                if(words.length != 3){
                    continue;
                }
                if(!words[1].equals("area") && !words[1].equals("pilot")){
                    continue;
                }
                if(!words[2].equals("asc") && !words[2].equals("dec")){
                    continue;
                }
                // command is a valid sort command
                boolean sortByArea;
                boolean sortAscending;
                if(words[1].equals("area")){
                    sortByArea = true;
                }else{
                    sortByArea = false;
                }
                if(words[2].equals("asc")){
                    sortAscending = true;
                }else{
                    sortAscending = false;
                }
                l.getHead().getObject().setCompareByArea(sortByArea);
                l.sort(sortAscending);

                String toPrint;
                if(words[1].equals("area")){
                    toPrint = "Head: " + String.format("%.2f", l.getHead().getObject().getArea()) + ", Tail: " + String.format("%.2f", l.getTail().getObject().getArea());
                }else{
                    toPrint = "Head: " + l.getHead().getObject().getName() + ", Tail: " +l.getTail().getObject().getName();
                }
                resultsOut.println(toPrint);
            }
        }

        areasOut.print(l.toString());

        userIn.close();
        routesIn.close();
        commandsIn.close();
        resultsOut.close();
        areasOut.close();
    }

    private static String search(LinkedList<Payload> list, double area){
        Node<Payload> node = list.getHead();
        // to check if area matches to 2 decimal places,
        // multiply by 100 and round to nearest integer 
        while(node.getNext() != null && Math.round(node.getObject().getArea() * 100) != Math.round(area * 100))
            node = node.getNext();
        if(Math.round(node.getObject().getArea() * 100) != Math.round(area * 100))
            return area + " not found";
            return node.getObject().getName() + " " + String.format("%.2f", node.getObject().getArea());
        // return area + " found";
    }

    private static String search(LinkedList<Payload> list, String name){
        Node<Payload> node = list.getHead();
        while(node.getNext() != null && node.getObject().getName().equals(name) == false)
            node = node.getNext();
        if(node.getObject().getName().equals(name) == false)
            return name + " not found";
        return node.getObject().getName() + " " + String.format("%.2f", node.getObject().getArea());
        // return name + " found";
    }

    // // parse the input file for coordinates and save them in masterList
    // private static void getCoordinates(Scanner input){
    //     String line = input.nextLine();
    //     line = line.trim();
    //     String[] coords = line.split(" ");

    //     // split the coordinates into x and y, then save those values in masterList
    //     for (int i = 0;i < coords.length; i++){
    //         String[] xy = coords[i].split(",");
    //         masterList[pilotNum][i][0] = Double.parseDouble(xy[0]);
    //         masterList[pilotNum][i][1] = Double.parseDouble(xy[1]);
    //     }

    //     // save the last real coordinate so that area doesn't calculate using extra zeros
    //     lastElem[pilotNum] = coords.length;
    // }

    // parse the input file for coordinates and save them in masterList
    private static void parseCoords(Scanner input, PrintWriter out){
        String line = input.nextLine();
        line = line.trim();

        int firstCoord = line.indexOf(","); // TODO check if -1?
        int numWordsPilotName = 0;
        int x = 0;
        int lastWord = line.indexOf(" ", x); // TODO check if -1?
        while(lastWord < firstCoord){
            x = lastWord + 1;
            lastWord = line.indexOf(" ", x) + 1;
            numWordsPilotName++;
        }

        if(numWordsPilotName < 1){
            // invalid input
            return;
        }

        String[] words = line.split(" ");

        String pilotName = "";
        for(int i = 0; i < numWordsPilotName; i++){
            pilotName += words[i] + " ";
        }
        pilotName = pilotName.trim(); // get rid of trailing space

        // check if pilot name is correct format
        if(!isValidName(pilotName)){
            return;
        }

        String[] coords = new String[words.length - numWordsPilotName];
        for(int i = 0; i < coords.length; i++){
            coords[i] = words[i+numWordsPilotName];
        }

        // split the coordinates into x and y, then save those values in masterList
        for (int i = 0;i < coords.length; i++){
            String[] xy = coords[i].split(",");
            if(xy.length != 2){
                // invalid input coords
                return;
            }
            double coordX;
            double coordY;
            try{
                coordX = Double.parseDouble(xy[0]);
                coordY = Double.parseDouble(xy[1]);
                masterList[pilotNum][i][0] = coordX;
                masterList[pilotNum][i][1] = coordY;
            }catch(Exception e){
                System.out.println(e);
                return;
            }
        }

        // save the last real coordinate so that area doesn't calculate using extra zeros
        lastElem[pilotNum] = coords.length;

        pilotList[pilotNum] = pilotName;
        // increment pilot number
        pilotNum++;
    }

    private static boolean isValidName(String name){
        char[] pilotChars = name.toCharArray();
        for(char ch : pilotChars){
            if(ch >= 48 && ch <= 57){
                // 0-9
            }else if(ch >= 65 && ch <= 90){
                // A-Z
            }else if(ch >= 97 && ch <= 122){
                // a-z
            }else if(ch == 39 || ch == 45 || ch == 32){
                // ' or - or  'space'
            }else{
                // invalid pilot name
                return false;
            }
        }
        // found no invalid chars
        return true;
    }

    // calculate the area of a given pilot based on his number as input
    private static double calculateArea(int pilot){
        double area = 0.0;
        for (int i = 1;i < lastElem[pilot]; i++){
            double op1 = (masterList[pilot][i-1][0] + masterList[pilot][i][0]);
            double op2 = (masterList[pilot][i-1][1] - masterList[pilot][i][1]);
            area = area + op1 * op2;
        }
        area = 0.5 * Math.abs(area);
        return area;
    }
    
}