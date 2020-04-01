
public class BinTree<T extends Comparable<T>> {
    /*
    Project 3
    Drew Pulliam
    dtp180003
    */

    private Node<T> rootNode;

    public BinTree(){
        rootNode = null;
    }

    public BinTree(Node<T> initialNode){
        initialNode.setLeft(null);
        initialNode.setRight(null);
        rootNode = initialNode;
    }

    public void add(Node<T> newNode){
        if(rootNode == null){
            // first node in list
            newNode.setLeft(null);
            newNode.setRight(null);
            rootNode = newNode;
        }else{
            // find where to put newNode
            add(newNode, rootNode);
        }
    }
    public void add(Node<T> newNode, Node<T> current){
        if(current != null){
            if(newNode.compareTo(current) < 0){
                // go left
                if(current.getLeft() == null){
                    // newNode goes here
                    current.setLeft(newNode);
                    return;
                }else{
                    add(newNode, current.getLeft());
                }
            }else if(newNode.compareTo(current) > 0){
                // go right
                if(current.getRight() == null){
                    // newNode goes here
                    current.setRight(newNode);
                    return;
                }else{
                    add(newNode, current.getRight());
                }
            }else{
                // node already exists
                return;
            }
        }

    }

    // public Node<T> getRoot(){
    //     return rootNode;
    // }
    // public void setRoot(final Node<T> rootNode) {
    //     this.rootNode = rootNode;
    // }

    public String databaseOutput(){
        // TODO
        // needs to be breadth-first
        return printInorder(rootNode);
    }

    private String printInorder(Node<T> node){
        String result = "";
        if(node == null)
            return "";
        result += printInorder(node.getLeft());
        result += node.toString();
        result += printInorder(node.getRight());

        return result;
    }
    
}