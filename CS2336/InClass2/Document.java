
public class Document {

    /*
    In Class Assignment 1
    Drew Pulliam
    dtp180003
    Dustyn Beutelspacher
    dgb180000
    */

    protected String body;


    public Document(){
        body = "";
    }

    public Document(String s){
        body = s;
    }

    public String getBody(){
        return body;
    }

    public void setBody(String s){
        body = s;
    }

    @Override
    public String toString(){
        return body;
    }

}