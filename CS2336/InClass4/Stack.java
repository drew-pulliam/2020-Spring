import java.util.LinkedList;

public class Stack {
    /*
    In Class 4
    Drew Pulliam
    dtp180003
    Dustyn Beutelspacher
    dgb180000
    */

    LinkedList<String> list = new LinkedList<String>();

    public Stack(){

    }

    public void push(String str){
        list.push(str);;
    }

    public String pop(){
        return list.removeFirst();
    }

    public String getFirst(){
        return list.peekFirst();
    }

}