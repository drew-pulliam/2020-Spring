import java.util.HashMap;

public class Graph {
    /*
    Project 4
    Drew Pulliam
    dtp180003
    */

    HashMap<String, Integer> galaxy;
    int curIndex = 0;
    int matrix[][];
    int maxSize;
    int curSize;

    public Graph(){
        galaxy = new HashMap<String, Integer>();
        maxSize = 100;
        matrix = new int[maxSize][maxSize];
        for(int i = 0; i < maxSize; i++){
            for(int j = 0; j < maxSize; j++){
                matrix[i][j] = -1;
            }
        }
        curSize = 0;
    }

    public void insertEdges(String line){
        String[] edges = line.split(" ");
        String rowName = edges[0];
        int rowIndex = galaxy.get(rowName);
        for(int i = 1; i < edges.length; i++){
            String[] curEdge = edges[i].split(",");
            String colName = curEdge[0];
            int colIndex = galaxy.get(colName);
            int weight = Integer.parseInt(curEdge[1]);
            matrix[rowIndex][colIndex] = weight;
        }
    }

    public void insertVertex(String planet){
        galaxy.put(planet, curIndex);
        curIndex ++;
    }

    public int getEdge(String startPlanet, String endPlanet){
        try {
            // check if it is a valid edge, if not return -1
            return matrix[galaxy.get(startPlanet)][galaxy.get(endPlanet)];
        } catch (Exception e) {
            return -1;
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
}