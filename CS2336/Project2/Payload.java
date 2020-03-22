public class Payload implements Comparable<Payload> {
    /*
    Project 2
    Drew Pulliam
    dtp180003
    */

    private String name;
    private double area;
    private static boolean compareByArea;

    public Payload(){
        compareByArea = true;
    }

    public Payload(String name){
        this();
        this.name = name;
    }

    public Payload(String name, double area){
        this(name);
        this.area = area;
    }

    @Override
    public int compareTo(Payload otherPayload) {
        // compare either by area or by name
        if(compareByArea){
            return (this.area > otherPayload.area) ?  1 : (this.area < otherPayload.area) ? -1 : 0;
        }else{
            return ((this.name).compareTo(otherPayload.name) > 0) ? 1 : ((this.name).compareTo(otherPayload.name) < 0) ? -1 : 0;
        }
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setArea(double area){
        this.area = area;
    }
    public double getArea(){
        return area;
    }

    public void setCompareByArea(boolean compareByArea){
        Payload.compareByArea = compareByArea;
    }
    public Boolean getCompareByArea(){
        return compareByArea;
    }

    @Override
    public String toString(){
        // return area rounded to 2 decimal places
        String formattedArea = String.format("%.2f", area);
        return name + "\t" + formattedArea;
    }
    
}