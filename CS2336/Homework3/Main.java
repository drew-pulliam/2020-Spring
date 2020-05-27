
public class Main {
    /*
    Homework 3
    Drew Pulliam
    dtp180003
    */

    public static void main(String[] args) {
        HashMap map = new HashMap();
        // add enough numbers to force rehashing twice (total size should be 47)
        map.put(11);
        map.put(7);
        map.put(4);
        map.put(22);
        map.put(33);
        map.put(44);
        map.put(55);
        map.put(3);
        map.put(5);
        map.put(6);
        map.put(8);
        map.put(9);
        map.put(10);
        map.put(12);
        System.out.println(map.toString());
    }
}