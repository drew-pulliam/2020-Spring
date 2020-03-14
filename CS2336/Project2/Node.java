public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    /*
    Project 2
    Drew Pulliam
    dtp180003
    */

    private T object;
    private Node<T> nextNode;
    private Node<T> prevNode;

    public Node(){
        this.object = null;
    }

    public Node(T object){
        this.object = object;
    }

    public void setNext(Node<T> nextNode){
        this.nextNode = nextNode;
    }
    public Node<T> getNext(){
        return nextNode;
    }

    public void setPrev(Node<T> prevNode){
        this.prevNode = prevNode;
    }
    public Node<T> getPrev(){
        return prevNode;
    }

    public T getObject(){
        return object;
    }

    @Override
    public int compareTo(Node<T> otherNode) {
        return (this.object).compareTo(otherNode.object);
    }

    @Override
    public String toString(){
        return object.toString();
    }
    
}