public class NonInteger extends Exception {
    /*
    In Class 3
    Drew Pulliam
    dtp180003
    Chris Thornsberry
    CRT180000
    */

    private String NonInt;

    public NonInteger(String nonInt){
        super(nonInt + " is not an integer");
        this.NonInt = nonInt;
    }

    public String getNonInt(){
        return NonInt;
    }
}