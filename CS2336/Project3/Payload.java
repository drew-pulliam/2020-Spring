public class Payload implements Comparable<Payload> {
    /*
    Project 3
    Drew Pulliam
    dtp180003
    */

    private String name;
    private int highScore;
    private String initials;
    private int plays;

    public Payload(){
        
    }

    public Payload(String name){
        this.name = name;
    }

    public Payload(String name, int highScore, String initials, int plays){
        this.name = name;
        this.highScore = highScore;
        this.initials = initials;
        this.plays = plays;
    }

    @Override
    public int compareTo(Payload otherPayload) {
        return ((this.name).compareTo(otherPayload.name) > 0) ? 1 : ((this.name).compareTo(otherPayload.name) < 0) ? -1 : 0;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setHighScore(int highScore){
        this.highScore = highScore;
    }
    public int getHighScore(){
        return highScore;
    }

    public void setInitials(String initials){
        this.initials = initials;
    }
    public String getInitials(){
        return initials;
    }

    public void setPlays(int plays){
        this.plays = plays;
    }
    public int getPlays(){
        return plays;
    }

    public double getRevenue(){
        return plays / 4.0; // 0.25 per play
    }

    @Override
    public String toString(){
        String formattedRevenue = String.format("%.2f", getRevenue());
        return name + ", " + highScore + ", " + initials + ", " + plays + ", $" + formattedRevenue + "\n";
    }

    public String basicLogOutput(){
        String formattedRevenue = String.format("%.2f", getRevenue());
        return "High Score: " + highScore + "\nInitials: " + initials + "\nPlays: " + plays + "\nRevenue: $" + formattedRevenue + "\n";
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Payload){
            return this.name.contains(((Payload)o).name);
        }
        return false;
    }
    
}