
public class File extends Document {

    /*
    In Class Assignment 1
    Drew Pulliam
    dtp180003
    Dustyn Beutelspacher
    dgb180000
    */

    private String pathname;
    private String filename;


    public File(String path, String name, String b){
        super(b);
        pathname = path;
        filename = name;
    }

    public String getPathname(){
        return pathname;
    }
    public void setPathname(String s){
        pathname = s;
    }

    public String getFilename(){
        return filename;
    }
    public void setFilename(String s){
        filename = s;
    }

    @Override
    public String toString(){
        return String.format("%s%s%n%s", pathname, filename, this.getBody());
    }
}