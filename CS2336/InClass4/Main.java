import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    /*
    In Class 4
    Drew Pulliam
    dtp180003
    Dustyn Beutelspacher
    dgb180000
    */

    public static void main(String[] args) throws IOException{
        Stack stack = new Stack();
        Scanner sc = new Scanner(new File("input.txt"));
        String input = sc.nextLine();
        String[] inputs = input.split(" ");
        PrintWriter output = new PrintWriter(new File("output.txt"));
        for (String s : inputs){
            switch (s){
                case "+":
                    // check ^ / * + -
                    if(stack.getFirst() != null){
                        while(stack.getFirst().equals("+") || stack.getFirst().equals("-")){
                            output.print(stack.pop());
                            if(stack.getFirst() == null){
                                break;
                            }
                        }
                    }
                    stack.push(s);
                    break;
                case "-":
                    // check ^ / * + -
                    if(stack.getFirst() != null){
                    while(stack.getFirst().equals("+") || stack.getFirst().equals("-")){
                        output.print(stack.pop());
                        if(stack.getFirst() == null){
                            break;
                        }
                    }
                    }
                    stack.push(s);
                    break;    
                case "*":
                    // check ^ / *
                    if(stack.getFirst() != null){
                    while(stack.getFirst().equals("^") || stack.getFirst().equals("/") || stack.getFirst().equals("*")){
                        output.print(stack.pop());
                        if(stack.getFirst() == null){
                            break;
                        }
                    }
                    }
                    stack.push(s);
                    break; 
                case "/":
                    // check ^ / *
                    if(stack.getFirst() != null){
                    while(stack.getFirst().equals("^") || stack.getFirst().equals("/") || stack.getFirst().equals("*")){
                        output.print(stack.pop());
                        if(stack.getFirst() == null){
                            break;
                        }
                    }
                    }
                    stack.push(s);

                    break;
                case "^":
                    // check ^
                    if(stack.getFirst() != null){
                    if(stack.getFirst().equals("^") == false){
                        stack.push(s);
                    }else{
                        output.print(s);
                    }
                    }else{
                        stack.push(s);
                    }
                    stack.push(s);
                    break;    
                case "(":
                    stack.push(s);
                    break;
                case ")":
                    // pop until (
                    if(stack.getFirst() != null){
                    while(stack.getFirst() != null && stack.getFirst().equals("(") == false){
                        
                        System.out.println(stack.getFirst());
                        output.print(stack.pop());
                        if(stack.getFirst() == null){
                            break;
                        }
                    }
                    }
                    if(stack.getFirst().equals("(")){
                        stack.pop();
                    }
                    break;
                default:
                    output.print(s);
                    break;
            }
        }
        while(stack.getFirst()!= null){
            output.print(stack.pop());
        }
        output.close();
    }
    
}