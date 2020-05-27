import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
    private void add(Node<T> newNode, Node<T> current){
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

    public ArrayList<Node<T>> search(Node<T> searchNode){
        if(rootNode == null){
            // empty tree
            return null;
        }else{
            ArrayList<Node<T>> matches = new ArrayList<Node<T>>();
            search(searchNode, rootNode, matches);
            return matches;
        }
    }
    private void search(Node<T> searchNode, Node<T> current, ArrayList<Node<T>> matches){
        // traverse tree inorder and append matches to arraylist
        if(current.getLeft() != null)
            search(searchNode, current.getLeft(), matches);
        if(current.equals(searchNode)){
            // this is the right node
            matches.add(current);
        }
        if(current.getRight() != null)
            search(searchNode, current.getRight(), matches);
    }

    public Node<T> delete(Node<T> deleteNode){
        if(deleteNode.compareTo(rootNode) == 0){
            // delete the root node
            Node<T> deleteCopy = rootNode;
            if(rootNode.getLeft() == null && rootNode.getRight() == null){
                // node is a leaf
                rootNode = null;
            }else if(rootNode.getLeft() != null && rootNode.getRight() == null){
                // node only has a left child
                Node<T> temp = rootNode.getLeft();
                rootNode = temp;
            }else if(rootNode.getLeft() == null && rootNode.getRight() != null){
                // node only has a right child
                Node<T> temp = rootNode.getRight();
                rootNode = temp;
            }else{
                // node has two children
                Node<T> leftMax = rootNode.getLeft();
                Node<T> leftMaxParent = rootNode;
                if(leftMax.getRight() == null){
                    // rootNode.getLeft IS the leftMax
                    rootNode.setLeft(leftMax.getLeft());
                }else{
                    while(leftMax.getRight() != null){
                        leftMaxParent = leftMax;
                        leftMax = leftMaxParent.getRight();
                    }
                    leftMaxParent.setRight(null);
                }
                Node<T> temp = rootNode;
                leftMax.setLeft(temp.getLeft());
                leftMax.setRight(temp.getRight());
                rootNode = leftMax;
            }
            return deleteCopy;
        }else{
            return delete(deleteNode, rootNode);
        }
    }
    private Node<T> delete(Node<T> deleteNode, Node<T> current){
        if(current != null){
            if(deleteNode.compareTo(current) < 0){
                // go left
                if(deleteNode.compareTo(current.getLeft()) == 0){
                    // this is the right node
                    Node<T> deleteCopy = current.getLeft();
                    if(current.getLeft().getLeft() == null && current.getLeft().getRight() == null){
                        // node is a leaf
                        current.setLeft(null);
                    }else if(current.getLeft().getLeft() != null && current.getLeft().getRight() == null){
                        // node only has a left child
                        Node<T> temp = current.getLeft().getLeft();
                        current.setLeft(temp);
                    }else if(current.getLeft().getLeft() == null && current.getLeft().getRight() != null){
                        // node only has a right child
                        Node<T> temp = current.getLeft().getRight();
                        current.setLeft(temp);
                    }else{
                        // node has two children
                        Node<T> leftMax = current.getLeft().getLeft();
                        Node<T> leftMaxParent = current.getLeft();
                        while(leftMax.getRight() != null){
                            leftMaxParent = leftMax;
                            leftMax = leftMaxParent.getRight();
                        }
                        leftMaxParent.setRight(null);
                        Node<T> temp = current.getLeft().getLeft();
                        leftMax.setLeft(temp.getLeft());
                        leftMax.setRight(temp.getRight());
                        current.getLeft().setLeft(leftMax);
                    }
                    return deleteCopy;
                }else{
                    return delete(deleteNode, current.getLeft());
                }
            }else if(deleteNode.compareTo(current) > 0){
                // go right
                if(deleteNode.compareTo(current.getRight()) == 0){
                    // this is the right node
                    Node<T> deleteCopy = current.getRight();
                    if(current.getRight().getLeft() == null && current.getRight().getRight() == null){
                        // node is a leaf
                        current.setRight(null);
                    }else if(current.getRight().getLeft() != null && current.getRight().getRight() == null){
                        // node only has a left child
                        Node<T> temp = current.getRight().getLeft();
                        current.setRight(temp);
                    }else if(current.getRight().getLeft() == null && current.getRight().getRight() != null){
                        // node only has a right child
                        Node<T> temp = current.getRight().getRight();
                        current.setRight(temp);
                    }else{
                        // node has two children
                        Node<T> leftMax = current.getRight().getLeft();
                        Node<T> leftMaxParent = current.getRight();
                        while(leftMax.getRight() != null){
                            leftMaxParent = leftMax;
                            leftMax = leftMaxParent.getRight();
                        }
                        leftMaxParent.setRight(null);
                        Node<T> temp = current.getRight().getLeft();
                        leftMax.setLeft(temp.getLeft());
                        leftMax.setRight(temp.getRight());
                        current.getRight().setLeft(leftMax);
                    }
                    return deleteCopy;
                }else{
                    return delete(deleteNode, current.getRight());
                }
            }else{
                // should never get here
                return null;
            }
        }else{
            return null;
        }
    }

    public String databaseOutput(){
        // print database breadth-first
        return printBreadthFirst();
    }

    public String sort(String order){
        if(order.equals("asc")){
            // sort ascending
            return printInorder(rootNode);
        }else{
            // sort descending
            return printReverseInorder(rootNode);
        }
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

    private String printReverseInorder(Node<T> node){
        String result = "";
        if(node == null)
            return "";
        result += printReverseInorder(node.getRight());
        result += node.toString();
        result += printReverseInorder(node.getLeft());

        return result;
    }
    
    public String printBreadthFirst() {
        String result = "";
        Queue<Node<T>> queue = new LinkedList<Node<T>>() ;
        if (rootNode == null)
            return "";
        queue.clear();
        queue.add(rootNode);
        while(!queue.isEmpty()){
            Node<T> node = queue.remove();
            result += node.toString();
            // System.out.print(node + " ");
            if(node.getLeft() != null) queue.add(node.getLeft());
            if(node.getRight() != null) queue.add(node.getRight());
        }
        return result;
    }
    
}