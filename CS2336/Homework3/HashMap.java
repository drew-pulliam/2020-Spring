
public class HashMap {
    /*
    Homework 3
    Drew Pulliam
    dtp180003
    */

    final double LOAD_FACTOR = 0.5;
    int arraySize = 11;
    int[] map = new int[arraySize];
    int usedSpace = 0;
    int[] primes = {11,13,17,19,23,29,31,37,41,43,47,53,59,61};

    public HashMap(){
        
    }

    private int hash(int key){
        return key % arraySize;
    }

    public void put(int key){
        if((double)usedSpace / arraySize >= LOAD_FACTOR){
            // need to increase size and rehash table
            rehash();
        }

        int k = hash(key);
        int j = 1;
        if(map[k] == 0){
            // no probing needed for this value
            map[k] = key;
            usedSpace ++;
        }else{
            // collision, use quadratic probing
            int i = hash(k + (j * j));
            while(map[i] != key){
                if(map[i] == 0){
                    // empty spot
                    map[i] = key;
                    usedSpace ++;
                }else{
                    // increment j and recalculate i
                    j++;
                    i = hash(k + (j * j));
                }
            }
        }
    }

    private void rehash(){
        // find next prime > arraySize * 2
        int i = 0;
        while(primes[i] <= (arraySize * 2)){
            i++;
        }

        // save oldMap temporarily
        int[] oldMap = map;

        // change arraySize and create new map
        arraySize = primes[i];
        map = new int[arraySize];
        usedSpace = 0;

        // add everything from oldMap into the new map
        for(int x : oldMap){
            if(x != 0)
                put(x);
        }
    }

    @Override
    public String toString(){
        String result = "";
        result += "current size: " + arraySize + "\n";
        result += "current fill %: " + ((double)usedSpace / arraySize) + "\n";
        result += "map:\n";
        for (int i: map){
            result += i + " ";
        }
        return result;
    }
}