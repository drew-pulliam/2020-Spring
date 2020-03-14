import java.util.Scanner;
import java.io.*;

public class Main {
    /*
    In Class Assignment 1
    Drew Pulliam
    dtp180003
    Dustyn Beutelspacher
    dgb180000
    */

    public static void main(String[] args) throws IOException{
        Document d = new Document("random stuff");
        File f = new File("C:/Program Files/","stuff.txt","this is the body of the file.");
        EMail e = new EMail("In Class 2","John","Bob","email body");

        System.out.println(d.toString());
        System.out.println("\nContains keyword stuff: " + containsKeyword(d, "stuff")+"\n");
        System.out.println(f.toString());
        System.out.println("\nContains keyword stuff: " + containsKeyword(f, "stuff")+"\n");
        System.out.println(e.toString());
        System.out.println("\nContains keyword stuff: " + containsKeyword(e, "stuff")+"\n");

    }

    public static boolean containsKeyword(Document doc, String keyword){
        if (doc instanceof EMail){
            return ((EMail)doc).toString().contains(keyword);
        }else if(doc instanceof File){
            return ((File)doc).toString().contains(keyword);
        }else{
            // just a document
            return doc.getBody().contains(keyword);
        }
    }
    
}