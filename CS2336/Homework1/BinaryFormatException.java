public class BinaryFormatException extends Exception {
    /*
    Homework 1
    Drew Pulliam
    dtp180003
    */

    private String binaryString;

    public BinaryFormatException(String binaryString){
        super("Incorrectly formatted String: " + binaryString);
        this.binaryString = binaryString;
    }

    public String getBinaryString(){
        return binaryString;
    }
}