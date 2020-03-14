
public class EMail extends Document {

    /*
    In Class Assignment 1
    Drew Pulliam
    dtp180003
    Dustyn Beutelspacher
    dgb180000
    */

    private String sender;
    private String recipient;
    private String title;


    public EMail(String t, String s, String r,String b){
        super(b);
        title = t;
        sender = s;
        recipient = r;
    }

    public String getSender(){
        return sender;
    }
    public void setSender(String s){
        sender = s;
    }

    public String getRecipient(){
        return recipient;
    }
    public void setRecipient(String s){
        recipient = s;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String s){
        title = s;
    }

    @Override
    public String toString(){
        return String.format("Title: %s%nSender: %s%nRecipient: %s%n%s", title, sender, recipient, getBody());
    }
}