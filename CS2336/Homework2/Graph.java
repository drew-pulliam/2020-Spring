import java.util.Scanner;

public class Graph {
    /*
    Homework 2
    Drew Pulliam
    dtp180003
    */

    int[][] matrix;
    int maxSize;
    int curSize;

    public Graph(){
        maxSize = 10;
        matrix = new int[maxSize][maxSize];
        curSize = 0;
    }

    public Graph(int max){
        maxSize = max;
        matrix = new int[maxSize][maxSize];
        curSize = 0;
    }

    public boolean isEmpty(){
        return curSize == 0;
    }

    public void createGraph(Scanner inputFile){
        // first line is curSize
        String line = inputFile.nextLine();
        curSize = Integer.parseInt(line.trim());
        while(inputFile.hasNextLine()){
            line = inputFile.nextLine();
            String[] nums = line.split(" ");
            // populate the correct row of the matrix based on the input line
            for(int i = 1; i < nums.length; i++){
                matrix[Integer.parseInt(nums[0])-1][Integer.parseInt(nums[i])-1] = 1;
            }
        }
    }

    @Override
    public String toString(){
        String result = "\t";
        for(int i = 1; i <= maxSize; i++){
            result += i + " ";
        }
        result += "\n\n";
        for(int i = 0; i < maxSize; i++){
            result += (i+1) + "\t";
            for(int j = 0; j < maxSize; j++){
                result += matrix[i][j] + " ";
            }
            result += "\n";
        }
        return result;
    }

    public boolean isConnected(){
        // if graph is empty, it's not connected
        if(isEmpty())
            return false;
        int[] connected = new int[curSize];
        connected[0] = 1;
        depthFirstSearch(0, connected);
        for(int i : connected){
            if(i == 0){
                return false;
            }
        }
        return true;
    }

    private void depthFirstSearch(int start, int[] connected){
        // visit all nodes that are connected to start
        for(int i = 0; i < curSize; i++){
            if(matrix[start][i] > 0){
                // only visit nodes that haven't been visited yet
                if(connected[i] == 0){
                    connected[i] = 1;
                    depthFirstSearch(i, connected);
                }
            }
        }
    }
}