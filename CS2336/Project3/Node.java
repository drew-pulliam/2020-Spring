public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    /*
    Project 3
    Drew Pulliam
    dtp180003
    */

    private T object;
    private Node<T> leftNode;
    private Node<T> rightNode;
    // private Node<T> parentNode;

    public Node(){
        this.object = null;
    }

    public Node(T object){
        this.object = object;
    }

    public void setLeft(Node<T> leftNode){
        this.leftNode = leftNode;
    }
    public Node<T> getLeft(){
        return leftNode;
    }

    public void setRight(Node<T> rightNode){
        this.rightNode = rightNode;
    }
    public Node<T> getRight(){
        return rightNode;
    }

    // public void setParent(Node<T> parentNode){
    //     this.parentNode = parentNode;
    // }
    // public Node<T> getParent(){
    //     return parentNode;
    // }

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