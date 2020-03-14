
public class LinkedList<T extends Comparable<T>> {
    /*
    Project 2
    Drew Pulliam
    dtp180003
    */

    private Node<T> headNode;
    private Node<T> tailNode;

    public LinkedList(){
        headNode = null;
        tailNode = null;
    }

    public LinkedList(final Node<T> initialNode){
        initialNode.setPrev(null);
        initialNode.setNext(null);
        headNode = initialNode;
        tailNode = initialNode;
    }

    public void add(final Node<T> newNode){
        if(headNode == null){
            // first node in list
            newNode.setPrev(null);
            newNode.setNext(null);
            headNode = newNode;
            tailNode = newNode;
        }else{
            // append node to list
            tailNode.setNext(newNode);
            newNode.setPrev(tailNode);
            tailNode = newNode;
        }
    }

    public void sort(final boolean ascending){

        Node<T> compare = headNode;
        Node<T> test = headNode.getNext();
        Node<T> nextTest = test.getNext();
        
        while(test != null){
            while(compare != null && compare.compareTo(test) == (ascending ? 1 : -1)){
                compare.setNext(test.getNext());
                test.setPrev(compare.getPrev());
                compare.setPrev(test);
                test.setNext(compare);
                if(compare.getNext() == null){
                    // compare is tailNode
                    tailNode = compare;
                }else{
                    // there is a node after compare, set its prevNode to compare
                    compare.getNext().setPrev(compare);
                }
                if(test.getPrev() == null){
                    // test is headNode
                    headNode = test;
                }else{
                    // there is a node before test, set its nextNode to test
                    test.getPrev().setNext(test);
                }compare = test.getPrev();
            }
            test = nextTest;
            if(test != null){
                compare = test.getPrev();
                nextTest = test.getNext();
            }
        }
    }

    public Node<T> getHead(){
        return headNode;
    }
    public void setHead(final Node<T> headNode) {
        this.headNode = headNode;
    }

    public Node<T> getTail(){
        return tailNode;
    }
    public void setTail(final Node<T> tailNode) {
        this.tailNode = tailNode;
    }

    @Override
    public String toString(){
        String result = "";
        if(headNode != null){
            result += headNode.toString() + "\n";
            Node<T> next = headNode.getNext();
            while(next != null){
                result += next.toString() + "\n";
                next = next.getNext();
            }
        }
        return result.trim();
    }
    
}