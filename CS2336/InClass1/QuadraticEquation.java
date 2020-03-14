
public class QuadraticEquation {

    /*
    In Class Assignment 1
    Drew Pulliam
    dtp180003
    Aras Sahebi
    aps150530
    */

    private int a;
    private int b;
    private int c;


    public QuadraticEquation(){
        a = 0;
        b = 0;
        c = 0;
    }

    public QuadraticEquation(int aa, int bb, int cc){
        a = aa;
        b = bb;
        c = cc;
    }

    public int getA(){
        return a;
    }
    public int getB(){
        return b;
    }
    public int getC(){
        return c;
    }

    public double getDiscriminant(){
        //b^2 - 4ac
        double descriminant = (b * b) - (4 * a * c);
        return descriminant;
    }
    
    public double getRoot1(){
        // (-b + sqrt(b^2-4ac))/2a
        // returns 0 if discriminant is negative
        if(getDiscriminant() < 0){
            return 0;
        }else{
            return (-b) + (Math.sqrt(getDiscriminant()) / (2*a));
        }
    }

    public double getRoot2(){
        // returns (-b - sqrt(b^2-4ac))/2a
        // returns 0 if discriminant is negative
        if(getDiscriminant() < 0){
            return 0;
        }else{
            return (-b) - (Math.sqrt(getDiscriminant()) / (2*a));
        }
    }
}