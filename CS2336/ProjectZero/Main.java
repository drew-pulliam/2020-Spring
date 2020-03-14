import java.util.Scanner;
import java.io.*;

public class Main {
    /*
    Project Zero
    Drew Pulliam
    dtp180003
    */

    // declare variables
    private static String[] pilotList = new String[20];
    private static double[][][] masterList = new double[20][16][2];
    private static int pilotNum = 0;

    public static void main(String[] args) throws IOException{

        // initialize user input scanner and file output
        PrintWriter fileOut = new PrintWriter(new File("pilot_areas.txt"));
        Scanner userIn = new Scanner(System.in);

        // ask user for input file name and initialize file input scanner
        System.out.println("Please input filename: ");
        String userInput = userIn.nextLine();
        Scanner fileIn = new Scanner(new File(userInput));

        // loop through the entire input file, saving pilot names and coordinates
        while(fileIn.hasNextLine()){
            // save pilot name for output later
            String pilotName = fileIn.next();
            pilotList[pilotNum] = pilotName;
            
            // get coordinates
            getCoordinates(fileIn);
            
            // increment pilot number
            pilotNum++;
        }

        // calculate areas and output to file
        outputAreas(fileOut);

        userIn.close();
        fileIn.close();
        fileOut.close();
    }

    // parse the input file for coordinates and save them in masterList
    private static void getCoordinates(Scanner input){
        String line = input.nextLine();
        line = line.trim();
        String[] coords = line.split(" ");

        // split the coordinates into x and y, then save those values in masterList
        for (int i = 0;i < coords.length; i++){
            String[] xy = coords[i].split(",");
            masterList[pilotNum][i][0] = Double.parseDouble(xy[0]);
            masterList[pilotNum][i][1] = Double.parseDouble(xy[1]);
        }

        // set any extra coordinates equal to last (real) coordinate
        // this helps make sure the area calculation works if there are 
        // less than 20 coordinates and the last coordinate is not (0,0)
        for (int i = coords.length; i < masterList[pilotNum].length; i++){
            String[] xy = coords[coords.length - 1].split(",");
            masterList[pilotNum][i][0] = Double.parseDouble(xy[0]);
            masterList[pilotNum][i][1] = Double.parseDouble(xy[1]);
        }
    }

    // calculate the area of a given pilot based on his number as input
    private static double calculateArea(int pilot){
        double area = 0.0;
        for (int i = 1;i < masterList[pilot].length; i++){
            area += (masterList[pilot][i-1][0] + masterList[pilot][i][0]) 
                    * (masterList[pilot][i-1][1] - masterList[pilot][i][1]);
        }
        area = 0.5 * Math.abs(area);
        return area;
    }

    // calculate areas and output to file
    private static void outputAreas(PrintWriter output){
        for(int i = 0; i < pilotNum; i++){
            double area = calculateArea(i);
            String formattedArea = String.format("%.2f", area);
            output.println(pilotList[i] + "    " + formattedArea);
        }
    }
    
}